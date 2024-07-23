package com.microsoft.net.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Sender {
    public static void main(String[] args) throws IOException {
        // 设置UDP接收端口
        DatagramSocket datagramSocket = new DatagramSocket(9998);
        // 注意编码格式
        byte[] bytes = "hello 你好".getBytes("UTF-8");
        // 创建数据报
        DatagramPacket datagramPacket =
                new DatagramPacket(bytes, bytes.length, InetAddress.getByName("192.168.1.107"), 9999);

        datagramSocket.send(datagramPacket);

        // 接收数据
        byte[] bytes1 = new byte[1024];
        DatagramPacket datagramPacket1 = new DatagramPacket(bytes1, bytes1.length);

        datagramSocket.receive(datagramPacket1);

        // 进行拆包
        int length = datagramPacket1.getLength();
        byte[] data = datagramPacket1.getData();

        String s = new String(data, 0, length, "UTF-8");
        System.out.println(s);

        datagramSocket.close();
    }
}
