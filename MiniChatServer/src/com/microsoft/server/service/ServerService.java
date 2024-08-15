package com.microsoft.server.service;

import com.microsoft.DAO.JDBCUtils;
import com.microsoft.common.Message;
import com.microsoft.common.MessageTypeEnum;
import com.microsoft.common.User;
import com.microsoft.server.utils.ServerDao;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务端进行登录验证服务
 */
public class ServerService {
    // 一个用户socket对应一个连接服务端socket
    private User user = null;
    private ServerSocket serverSocket = null;

    /**
     * 验证也进行阻塞式验证 可以一直监听服务端的登录操作
     */
    public ServerService() {
        try {
            serverSocket = new ServerSocket(9999);
            System.out.println("服务端9999正在监听...");
            // 推送新闻
            new ServerNewsThread().start();
            // 初始化
            JDBCUtils.init();
            while (true) {
                Socket socket = serverSocket.accept();

                // 取得客户端用户信息
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                user = (User) ois.readObject();

                // 准备发送登录是否成功消息
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                Message message = new Message();

                if (ServerDao.chekUser(user)) {
                    System.out.println("用户: " + user.getId() + " 密码: " + user.getPwd() + " 验证成功");
                    // 发送成功消息
                    message.setMesType(MessageTypeEnum.LOGIN_SUCCESS.getCode());
                    oos.writeObject(message);
                    // 启动server与client连接的线程
                    ServerConnectClientThread serverConnectClientThread = new ServerConnectClientThread(socket, user.getId());
                    serverConnectClientThread.start();
                    // 将线程加入到集合中管理
                    ManageThread.addThread(serverConnectClientThread);

// 不要随便关闭流 可能导致后面socket在使用这个流时报错socket closed
//                    ois.close();
//                    oos.close();
                } else {
                    System.out.println("用户: " + user.getId() + " 密码: " + user.getPwd() + " 验证失败");
                    // 发送失败消息
                    message.setMesType(MessageTypeEnum.LOGIN_FAIL.getCode());
                    oos.writeObject(message);

                    ois.close();
                    oos.close();
                    socket.close();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // 服务器停止监听
            try {
                serverSocket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
