package com.microsoft.net;

import com.sun.org.apache.xpath.internal.operations.String;

import java.net.InetAddress;

public class Inet {
    public static void main(String[] args) throws Exception {
        // 创建一个本地主机的InetAddress对象
        InetAddress localHost = InetAddress.getLocalHost();
        InetAddress byName = InetAddress.getByName("www.baidu.com");
        // 得到对象的地址
        System.out.println(localHost.getAddress());
        System.out.println(byName.getAddress());

        // 得到ip地址
        System.out.println(localHost.getHostAddress());
        System.out.println(byName.getHostAddress());
        // 得到本机名/主机名 不会访问DNS服务器
        System.out.println(localHost.getHostName());
        System.out.println(byName.getHostName());
        // 如果是域名创建的对象 如果该主机名存在别名 那么返回别名（因为主机名比较复杂）
        // 如果通过ip地址创建的对象 那么会直接返回主机名
        System.out.println(localHost.getCanonicalHostName());
        System.out.println(byName.getCanonicalHostName());
        System.out.println(localHost);
        System.out.println(byName);
    }
}
