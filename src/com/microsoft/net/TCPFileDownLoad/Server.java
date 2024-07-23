package com.microsoft.net.TCPFileDownLoad;

import com.microsoft.net.StreamUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(9999);

        System.out.println("服务端等待连接");
        Socket socket = serverSocket.accept();


        StringBuilder stringBuilder = new StringBuilder();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String line = "";

        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }

        String downLoadFile = "";
        // 注意StringBuilder类中没有重写equals方法（默认比较内存地址） 所以需要转换为String类型进行比较
        if (stringBuilder.toString().equals("搁浅")) {
            downLoadFile = "搁浅.mp3";
        } else {
            downLoadFile = "晴天.mp3";
        }

        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(downLoadFile));

        byte[] buf = StreamUtils.streamToByteArray(bufferedInputStream);

        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());
        bufferedOutputStream.write(buf);
        bufferedOutputStream.flush();
        socket.shutdownOutput();

        System.out.println("文件发送成功");

        bufferedOutputStream.close();
        bufferedInputStream.close();
        bufferedReader.close();
        socket.close();
        serverSocket.close();
    }
}
