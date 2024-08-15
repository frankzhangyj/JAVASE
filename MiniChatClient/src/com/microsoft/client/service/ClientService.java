package com.microsoft.client.service;

import com.microsoft.common.Message;
import com.microsoft.common.MessageTypeEnum;
import com.microsoft.common.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
//TODO 目前一个用户只有一个socket线程
/**
 * 客户端服务类
 * 为客户端提供用户相关服务
 */
public class ClientService {
    private Socket socket;
    private User user;

    /**
     * 向服务端发送用户 判断该用户是否存在 并创建一个通信socket线程与服务端保持连接
     */
    public boolean checkUser(User user) {
        this.user = user;
        boolean flag = false;
        // 将用户信息发送到服务端
        try {
            socket = new Socket(InetAddress.getLocalHost(), 9999);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(user);
            // 得到服务器发送的验证信息
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Message message = (Message) ois.readObject();
            // 判断是否存在该用户
            if (message.getMesType().equals(MessageTypeEnum.LOGIN_SUCCESS.getCode())) {
                System.out.println("==========登录成功===========");
                flag = true;
                ClientConnectServeThread clientConnectServeThread = new ClientConnectServeThread(socket, user.getId());
                clientConnectServeThread.start();
                ManageThread.addThread(clientConnectServeThread);
// 不要随便关闭流 可能导致后面socket在使用这个流时报错socket closed
//                oos.close();
//                ois.close();

                return true;
            } else {
                System.out.println("==========登录失败===========");

                oos.close();
                ois.close();
                socket.close();

                return false;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 得到服务端发送的一个在线用户列表
     */
    public void onlineFriendList() {
        Message message = new Message();
        message.setSender(user.getId());
        message.setMesType(MessageTypeEnum.MESSAGE_GET_ONLINE_FRIEND.getCode());

        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 退出系统 避免主线程退出 但是socket线程还在运行
     */
    public void logout() {
        Message message = new Message();
        message.setSender(user.getId());
        message.setMesType(MessageTypeEnum.MESSAGE_CLIENT_EXIT.getCode());

        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(message);
            System.out.println("==========(用户 " + user.getId() + " )退出系统===========");
            System.exit(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
