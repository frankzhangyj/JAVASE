package com.microsoft.client.service;

import com.microsoft.common.Message;
import com.microsoft.common.MessageTypeEnum;
import lombok.Getter;
import lombok.Setter;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * 客户端Socket线程
 * 利用线程管理socket从服务端得到数据
 */
@Setter
@Getter
public class ClientConnectServeThread extends Thread {
    private Socket socket;
    private String userId;

    public ClientConnectServeThread() {
    }

    public ClientConnectServeThread(Socket socket, String userId) {
        this.socket = socket;
        this.userId = userId;
    }

    /**
     *客户端socket线程进行阻塞式通信
     */
    @Override
    public void run() {
        while (true) {
            try {
                // 接收服务端的message
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                // 如果服务端没有发送Message对象 那么线程就会发生阻塞
                Message message = (Message) ois.readObject();

                // 拉取用户列表
                if (message.getMesType().equals(MessageTypeEnum.MESSAGE_RETURN_ONLINE_FRIEND.getCode())) {
                    String[] onlineUsers = message.getContent().split(" ");
                    System.out.println("==========当前用户列表===========");
                    int cnt = 1;
                    for (String onlineUser : onlineUsers) {
                        System.out.println("用户" + cnt++ + ": " + onlineUser);
                    }
                    System.out.println("===============================");
                    // 接收消息
                } else if (message.getMesType().equals(MessageTypeEnum.MESSAGE_COMMON_MES.getCode())) {
                    String content = message.getContent();
                    String time = message.getSendTime();
                    System.out.println("===============================");
                    System.out.println(time + "\n" +content);
                    System.out.println("===============================");
                    // 接收文件
                } else if (message.getMesType().equals(MessageTypeEnum.MESSAGE_FILE_MES.getCode())) {
                    String sender = message.getSender();
                    String receiver = message.getReceiver();
                    String receAdd = message.getReceAdd();
                    byte[] buff = message.getFile();

                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(receAdd));
                    bufferedOutputStream.write(buff);
                    bufferedOutputStream.flush();
                    System.out.println("===============================");
                    System.out.println("接收到 " + sender + " 发送的文件 " + receAdd);
                    System.out.println("===============================");
                    // 接收群消息
                } else if (message.getMesType().equals(MessageTypeEnum.MESSAGE_GROUP_MES.getCode())) {
                    String content = message.getContent();
                    System.out.println("===============================");
                    System.out.println(content);
                    System.out.println("===============================");
                    // 加载离线消息
                } else if (message.getMesType().equals(MessageTypeEnum.MESSAGE_LOAD_MES.getCode())) {
                    String content = message.getContent();
                    String time = message.getSendTime();
                    System.out.println("===============================");
                    System.out.println(time + "\n" + content);
                    System.out.println("===============================");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }


}
