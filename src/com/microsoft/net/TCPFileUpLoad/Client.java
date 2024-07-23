package com.microsoft.net.TCPFileUpLoad;

import com.microsoft.net.StreamUtils;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket(InetAddress.getLocalHost(), 8888);

        // 将path中的文件输入到程序中
        String path = "D:\\视频与音乐\\kakaxi.jpg";

        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(path));
        byte[] array = StreamUtils.streamToByteArray(bufferedInputStream);


//        OutputStream outputStream = socket.getOutputStream();
//        outputStream.write(array);
        // 将二进制文件输出到服务端
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());
        bufferedOutputStream.write(array);
        bufferedOutputStream.flush();
        socket.shutdownOutput();

        // 输入服务端的回复信息
        System.out.println(StreamUtils.streamToString(socket.getInputStream()));

        bufferedInputStream.close();
        bufferedOutputStream.close();
        bufferedInputStream.close();
//        outputStream.close();
        socket.close();
    }
}
