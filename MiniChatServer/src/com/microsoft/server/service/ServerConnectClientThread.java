package com.microsoft.server.service;

import com.microsoft.common.Message;
import com.microsoft.common.MessageTypeEnum;
import com.microsoft.server.utils.ServerDao;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * 服务端socket线程
 * 利用线程管理socket从client得到的信息
 */
@Setter
@Getter
public class ServerConnectClientThread extends Thread {
    // 一个socket线程对应一个socket
    private Socket socket;
    private String userId;

    public ServerConnectClientThread() {
    }

    public ServerConnectClientThread(Socket socket, String userId) {
        this.socket = socket;
        this.userId = userId;
    }

    /**
     * 服务端socket线程进行阻塞式通信
     */
    @Override
    public void run() {
        while (true) {
            try {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message message = (Message) ois.readObject();

                // 拉取用户列表
                if (message.getMesType().equals(MessageTypeEnum.MESSAGE_GET_ONLINE_FRIEND.getCode())) {
                    System.out.println(message.getSender() + "请求拉取用户列表");

                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

                    String onlineUser = ManageThread.getOnlineUser();

                    Message message1 = new Message();
                    message1.setMesType(MessageTypeEnum.MESSAGE_RETURN_ONLINE_FRIEND.getCode());
                    message1.setContent(onlineUser);

                    oos.writeObject(message1);
                    // 私聊
                } else if (message.getMesType().equals(MessageTypeEnum.MESSAGE_COMMON_MES.getCode())) {
                    String receiver = message.getReceiver();
                    String sender = message.getSender();

                    if (ManageThread.checkOnlineUser(receiver)) {
                        // 用户在线
                        ServerConnectClientThread receiverSocketThread = ManageThread.getThread(receiver);

                        ObjectOutputStream oos = new ObjectOutputStream(receiverSocketThread.getSocket().getOutputStream());
                        oos.writeObject(message);
                    } else {
                        // 用户离线 加载消息到数据库
                        ServerDao.storeOfflineMessage(message);
                    }
                    // 群发
                } else if (message.getMesType().equals(MessageTypeEnum.MESSAGE_GROUP_MES.getCode())) {
                    String sender = message.getSender();

                    ArrayList<ServerConnectClientThread> serverConnectClientThreads = ManageThread.getAllThread();

                    for (ServerConnectClientThread serverConnectClientThread : serverConnectClientThreads) {
                        if (serverConnectClientThread.getUserId().equals(sender)) {
                            continue;
                        }

                        ObjectOutputStream oos = new ObjectOutputStream(serverConnectClientThread.getSocket().getOutputStream());
                        oos.writeObject(message);
                    }
                    // 发送文件
                } else if (message.getMesType().equals(MessageTypeEnum.MESSAGE_FILE_MES.getCode())) {
                    String receiver = message.getReceiver();

                    ObjectOutputStream oos = new ObjectOutputStream(ManageThread.getThread(receiver).getSocket().getOutputStream());
                    oos.writeObject(message);
                    // 退出系统
                } else if (message.getMesType().equals(MessageTypeEnum.MESSAGE_CLIENT_EXIT.getCode())) {
                    ManageThread.removeThread(socket);
                    socket.close();
                    System.out.println("用户: " + userId + " 退出系统");
                    break;
                } else if (message.getMesType().equals(MessageTypeEnum.MESSAGE_LOAD_MES.getCode())) {

                    // 将数据库中的所有待发消息 传回客户端
                    List<String> offlineMessage = ServerDao.getOfflineMessage(message.getSender());

                    if (offlineMessage.size() != 0) {
                        for (String mes : offlineMessage) {
                            String[] con = mes.split("/");
                            Message message1 = new Message();
                            message1.setMesType(MessageTypeEnum.MESSAGE_LOAD_MES.getCode());
                            message1.setSender(con[0]);
                            message1.setReceiver(con[1]);
                            message1.setContent(con[2]);
                            message1.setSendTime(con[3]);

                            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                            oos.writeObject(message1);
                        }
                        // 删除加载过的离线消息
                        ServerDao.removeOfflineMessage(message.getSender());
                    }
                } else {
                    System.out.println("异常");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
