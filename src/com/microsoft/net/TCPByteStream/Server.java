package com.microsoft.net.TCPByteStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        // 服务端创建一个severSocket端口表示本地9999端口
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("9999端口等待连接");
        // 创建一个端口来监听 当没有客户端连接时发生阻塞
        Socket socket = serverSocket.accept();
        System.out.println("服务端 socket " + socket.getClass());

        // 创建一个socket关联的输入流 将客户端的数据以字节流的形式输入
        InputStream inputStream = socket.getInputStream();
        // 缓冲数组
        byte[] buff = new byte[1024];

        int len = 0;
        // 读取字节流
        while ((len = inputStream.read(buff)) != -1) {
            System.out.println(new String(buff, 0, len));
        }

        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("hello client".getBytes());
        // 在输出结束后需要添加一个结束标记
        socket.shutdownOutput();

        // 关闭
        outputStream.close();
        inputStream.close();
        socket.close();
        serverSocket.close();

        System.out.println("服务端关闭");
    }
}
