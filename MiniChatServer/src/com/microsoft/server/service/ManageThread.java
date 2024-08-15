package com.microsoft.server.service;

import java.net.Socket;
import java.util.*;

/**
 * 管理socket线程
 * 定义一个hashTable 其中一个socket对应一个线程 一个用户可能与服务端建立多个socket 即一个用户有多个socket
 */
public class ManageThread {
    private static HashMap<Socket, ServerConnectClientThread> hashMap = new HashMap<>();

    /**
     * 添加socket线程
     * @param serverConnectClientThread 服务端socket线程
     */
    public static void addThread(ServerConnectClientThread serverConnectClientThread) {
        hashMap.put(serverConnectClientThread.getSocket(), serverConnectClientThread);
    }

    /**
     *移除socket线程
     * @param socket
     */
    public static void removeThread(Socket socket) {
        hashMap.remove(socket);
    }

    //TODO 如何做到一个用户有多个socket 如何管理
    /**
     * 得到socket线程
     * @param userId 用户id
     * @return
     */
    public static ServerConnectClientThread getThread(String userId) {
        Set<Map.Entry<Socket, ServerConnectClientThread>> entries = hashMap.entrySet();

        Iterator<Map.Entry<Socket, ServerConnectClientThread>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<Socket, ServerConnectClientThread> next = iterator.next();
            if (next.getValue().getUserId().equals(userId)) {
                return next.getValue();
            }
        }
        return null;
    }

    /**
     * 得到所有正在运行的socket线程
     * @return
     */
    public static ArrayList<ServerConnectClientThread> getAllThread() {
        ArrayList<ServerConnectClientThread> serverConnectClientThreads = new ArrayList<>();

        Set<Map.Entry<Socket, ServerConnectClientThread>> entries = hashMap.entrySet();

        Iterator<Map.Entry<Socket, ServerConnectClientThread>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<Socket, ServerConnectClientThread> next = iterator.next();
            serverConnectClientThreads.add(next.getValue());
        }

        return serverConnectClientThreads;
    }

    /**
     * 得到用户列表
     * @return
     *
     */
    public static String getOnlineUser() {
        String onlineUser = "";
        Set<Map.Entry<Socket, ServerConnectClientThread>> entries = hashMap.entrySet();

        Iterator<Map.Entry<Socket, ServerConnectClientThread>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<Socket, ServerConnectClientThread> next = iterator.next();
            onlineUser += next.getValue().getUserId() + " ";
        }
        return onlineUser;
    }

    /**
     * 检查用户是否在线
     * @param userId 用户id
     * @return
     */
    public static boolean checkOnlineUser(String userId) {
        Set<Map.Entry<Socket, ServerConnectClientThread>> entries = hashMap.entrySet();

        Iterator<Map.Entry<Socket, ServerConnectClientThread>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<Socket, ServerConnectClientThread> next = iterator.next();
            if (next.getValue().getUserId().equals(userId)) {
                return true;
            }
        }
        return false;
    }
}
