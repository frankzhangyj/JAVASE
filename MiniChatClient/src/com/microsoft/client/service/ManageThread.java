package com.microsoft.client.service;

import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 管理socket线程
 */
public class ManageThread {
    // 一个socekt 对应一个线程
    private static HashMap<Socket, ClientConnectServeThread> hashMap = new HashMap<>();

    /**
     * 添加线程
     * @param clientConnectServeThread 客户端socket线程
     */
    public static void addThread(ClientConnectServeThread clientConnectServeThread) {
        hashMap.put(clientConnectServeThread.getSocket(), clientConnectServeThread);
    }

    /**
     * 通过socket得到socket线程
     * @param socket
     * @return
     */
    public static ClientConnectServeThread getThread(Socket socket) {
        return hashMap.get(socket);
    }

    /**
     * 通过用户id得到socket线程
     * @param userId 用户id
     * @return
     */
    public static ClientConnectServeThread getThread(String userId) {
        Set<Map.Entry<Socket, ClientConnectServeThread>> entries = hashMap.entrySet();

        Iterator<Map.Entry<Socket, ClientConnectServeThread>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<Socket, ClientConnectServeThread> next = iterator.next();
            if (next.getValue().getUserId().equals(userId)) {
                return next.getValue();
            }
        }
        return null;
    }
}
