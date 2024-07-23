package com.microsoft.net.TCPByteStream;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws Exception {
        // 创建一个客户端端口用来连接服务器端口号为9999的端口
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);

        System.out.println("客户端 " + socket.getClass());
        // 创建一个socket关联的输出流 将数据写入
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("hello sever".getBytes());
        // 在输出结束后需要添加一个结束标记
        socket.shutdownOutput();

        // 读取客户端输入的数据
        InputStream inputStream = socket.getInputStream();

        byte[] buff = new byte[1024];

        int len = 0;

        while ((len = inputStream.read(buff)) != -1) {
            System.out.println(new String(buff, 0, len));
        }

        // 关闭
        inputStream.close();
        outputStream.close();
        socket.close();

        System.out.println("客户端关闭");
    }
}
