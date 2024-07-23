package com.microsoft.net.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * UDP 是一种不可靠的传输 不会建立连接再传输 而是将数据封装成数据包 然后通过网络进行不可靠传输 包最大是64kb
 * UDP 没有固定的服务端和客户端 每个端口都可以作为接受端或者发送端
 */
public class Receiver {
    public static void main(String[] args) throws IOException {
        // 设置UDP接收端口
        DatagramSocket datagramSocket = new DatagramSocket(9999);

        // 设置数据包
        byte[] bytes = new byte[1024];

        DatagramPacket datagramPacket = new DatagramPacket(bytes,  bytes.length);

        System.out.println("等待");
        datagramSocket.receive(datagramPacket);

        // 进行拆包
        int length = datagramPacket.getLength();
        byte[] data = datagramPacket.getData();
        // 注意解码与编码格式相同
        String s = new String(data,0, length, "UTF-8");
        System.out.println(s);

        byte[] bytes1 = "你妈的 fuck".getBytes("UTF-8");
        // 注意端口
        DatagramPacket datagramPacket1 =
                new DatagramPacket(bytes1, bytes1.length, InetAddress.getByName("192.168.1.107"), 9998);

        datagramSocket.send(datagramPacket1);

        datagramSocket.close();
    }
}
