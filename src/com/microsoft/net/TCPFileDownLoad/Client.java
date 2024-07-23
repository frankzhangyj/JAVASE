package com.microsoft.net.TCPFileDownLoad;

import com.microsoft.net.StreamUtils;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket(InetAddress.getByName("192.168.1.107"), 9999);

        String downLoadFile = "";
        System.out.println("输入需要下载的歌曲");
        Scanner scanner = new Scanner(System.in);
        downLoadFile = scanner.next();

        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bufferedWriter.write(downLoadFile);
        bufferedWriter.flush();
        socket.shutdownOutput();

        if (downLoadFile.equals("搁浅")) {
            downLoadFile = "E://" + downLoadFile + ".mp3";
        } else {
            downLoadFile = "E://" + "无名" + ".mp3";
        }

        BufferedInputStream bufferedInputStream = new BufferedInputStream(socket.getInputStream());
        byte[] buf = StreamUtils.streamToByteArray(bufferedInputStream);

        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(downLoadFile));
        bufferedOutputStream.write(buf);
        bufferedOutputStream.flush();
        System.out.println("文件下载成功");

        bufferedOutputStream.close();
        bufferedInputStream.close();
        bufferedWriter.close();
        socket.close();
    }
}
