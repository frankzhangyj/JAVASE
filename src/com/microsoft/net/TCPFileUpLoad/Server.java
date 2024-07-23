package com.microsoft.net.TCPFileUpLoad;

import com.microsoft.net.StreamUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8888);
        // 在连接到服务器后 系统会自动给客户端分配一个socket来进行操作
        // windows利用netstat -an | more  来进行查看当前主机上的所有网络连接TCP and UDP 
        System.out.println("服务端等待连接");
        Socket socket = serverSocket.accept();
        System.out.println("服务端接口 " + socket.getClass());

        // 输入客户端上传的二进制文件
        BufferedInputStream bufferedInputStream = new BufferedInputStream(socket.getInputStream());

        byte[] array = StreamUtils.streamToByteArray(bufferedInputStream);

//TODO: socket.shutDownOutPut 作用 outputstream.close 作用

/*
   socket.shutdownOutput() 用于只关闭输出流部分，以通知对方数据发送完毕，但 Socket 仍然可以接收数据（输入流保持打开状态）
        如果后面还需要输出 那么就只能重建socket连接
   OutputStream.close()：关闭输出流，并且会释放流相关的所有资源，通常会导致整个 Socket 的关闭。适用于完成所有通信后彻底关闭连接的场景。
*/

        // 将得到的二进制文件输出到path路径
        String path = "picture02.jpg";

        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(path));
        bufferedOutputStream.write(array);
        bufferedOutputStream.flush();


        // 将回复信息输出到客户端
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bufferedWriter.write("文件上传成功");
        bufferedWriter.flush();
        socket.shutdownOutput();

        bufferedWriter.close();
        bufferedInputStream.close();
        bufferedOutputStream.close();
        socket.close();
        serverSocket.close();

    }
}
