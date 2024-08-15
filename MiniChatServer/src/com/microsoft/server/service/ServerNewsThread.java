package com.microsoft.server.service;

import com.microsoft.common.Message;
import com.microsoft.common.MessageTypeEnum;
import com.microsoft.server.utils.ScanUtils;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * 服务器推送新闻线程
 */
public class ServerNewsThread extends Thread {

    /**
     * 推送新闻socket线程进行阻塞式通信
     * 相当于群聊
     */
    @Override
    public void run() {
        while (true) {
            System.out.println("请输入服务端需要推送的新闻(输入exit退出推送服务): ");
            String news = ScanUtils.scanContent();
            if (news.equals("exit")) {
                break;
            }

            Message message = new Message();
            message.setMesType(MessageTypeEnum.MESSAGE_GROUP_MES.getCode());
            message.setSender("服务器");
            message.setContent("服务器 对 大家说: " + news);

            ArrayList<ServerConnectClientThread> serverConnectClientThreads = ManageThread.getAllThread();

            for (ServerConnectClientThread serverConnectClientThread : serverConnectClientThreads) {
                try {
                    ObjectOutputStream oos = new ObjectOutputStream(serverConnectClientThread.getSocket().getOutputStream());
                    oos.writeObject(message);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("新闻推送成功!");
        }
    }
}
