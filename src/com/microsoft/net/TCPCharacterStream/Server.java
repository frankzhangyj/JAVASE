package com.microsoft.net.TCPCharacterStream;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("9999端口等待连接");

        Socket socket = serverSocket.accept();
        System.out.println("服务端 socket " + socket.getClass());

        // 输入字符流
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String line;

        // 逐行读取数据
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println("Received from client: " + line);
        }

        // 输出字符流
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bufferedWriter.write("hello client");
        bufferedWriter.newLine(); // Add line separator
        bufferedWriter.flush();   // Ensure data is sent

        socket.shutdownOutput();

        bufferedReader.close();
        bufferedWriter.close();
        socket.close();
        serverSocket.close();
    }
}
