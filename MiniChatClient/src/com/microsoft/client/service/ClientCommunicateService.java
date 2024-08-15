package com.microsoft.client.service;

import com.microsoft.client.utils.ScanUtils;
import com.microsoft.client.utils.StreamUtils;
import com.microsoft.common.Message;
import com.microsoft.common.MessageTypeEnum;
import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 客户端之间聊天服务
 */
@Getter
@Setter
public class ClientCommunicateService {
    private Socket socket;
    private String userId;

    public ClientCommunicateService() {
    }

    public ClientCommunicateService(String userId) {
        this.userId = userId;
        this.socket = ManageThread.getThread(userId).getSocket();
    }

    /**
     * 设置userId
     * @param userId 用户id
     */
    public void setUserId(String userId) {
        this.userId = userId;
        this.socket = ManageThread.getThread(userId).getSocket();
    }

    /**
     * 发送私聊
     */
    public void privateChatsend() {
        String receiver;
        String content;
        System.out.println("请输入一个私聊的用户(在线)");
        receiver = ScanUtils.scanString();
        System.out.println("请输入聊天内容: ");
        content = userId + " 对 " + receiver + " 说: " + ScanUtils.scanContent();

        Message message = new Message();
        message.setMesType(MessageTypeEnum.MESSAGE_COMMON_MES.getCode());
        message.setSender(userId);
        message.setReceiver(receiver);
        message.setContent(content);
        // 格式化时间
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        message.setSendTime(dateFormat.format(new Date()));

        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(message);
            System.out.println("===============================");
            System.out.println(message.getSendTime()+"\n"+content);
            System.out.println("消息发送成功!");
            System.out.println("===============================");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 发送群聊
     */
    public void groupChatSend() {
        String content;
        System.out.println("请输入聊天内容: ");
        content = userId + " 对 大家 说: " + ScanUtils.scanContent();

        Message message = new Message();
        message.setMesType(MessageTypeEnum.MESSAGE_GROUP_MES.getCode());
        message.setSender(userId);
        message.setContent(content);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        message.setSendTime(dateFormat.format(new Date()));

        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(message);

            System.out.println(content);
            System.out.println("消息发送成功");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 发送文件
     */
    public void fileSend() {
        String receiver;
        String sendAdd;
        String receAdd;
        byte[] buff;

        System.out.println("请输入一个接收文件的用户: ");
        receiver = ScanUtils.scanString();

        System.out.println("请输入目标文件地址(格式为D:\\porject\\MiniChatClient\\sendFile\\xxx.jpg): ");
        sendAdd = ScanUtils.scanContent();

        System.out.println("请输入对方接收文件地址(格式为D:\\porject\\MiniChatClient\\receiveFile\\xxx.jpg): ");
        receAdd = ScanUtils.scanContent();
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(sendAdd));
            buff = StreamUtils.streamToByteArray(bufferedInputStream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Message message = new Message();
        message.setMesType(MessageTypeEnum.MESSAGE_FILE_MES.getCode());
        message.setSender(userId);
        message.setReceiver(receiver);
        message.setSendAdd(sendAdd);
        message.setReceAdd(receAdd);
        message.setFile(buff);

        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(message);
            System.out.println("文件发送成功!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 加载离线消息
     */
    public void loadOfflineMessage() {
        Message message = new Message();
        message.setSender(userId);
        message.setMesType(MessageTypeEnum.MESSAGE_LOAD_MES.getCode());

        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
