package com.microsoft.stringBuilder;

import java.time.temporal.ValueRange;

public class StringDemo {
    public static void main(String[] args) {
        // String 实现charSequence接口 创建的字符串存储在字符串常量池中，
//         编译器会对字符串运算符进行优化，使得不需要创建多余的字符串对象。
        String str1 = "asdfasf" + "asdfasdf";
        // 而 new 创建的字符串对象在堆上
        String str2 = new String(";ajkfadlf");
        // String 类是不可改变的，所以你一旦创建了 String 对象，那它的值就无法改变了
        // 因此循环拼接字符串时应避免使用String 使用StringBuilder 存在已经定义的变量，则会调用StringBuilder进行拼接操作
        // 这里str1重新申请了一块内存 中间过程使用StringBuilder 来进行字符串拼接
        str1 = str1 + str2;

        // StringBuffer 和 StringBuilder 字符串可以更改 继承AbstractStringBuilder 基本方法和String类似
        // StringBuffer 线程安全（StringBuffer 的所有公开方法都是 synchronized 修饰的）
        // 效率低 它的所有公开方法都是同步的 会对其加锁
        // 具有缓冲区 默认大小16 避免扩展时频繁分配内存 每次一倍增加 字符串都存储在缓冲区中
        // 在多线程环境下使用
        StringBuffer str3 = new StringBuffer("asdfasd");
        str3.append(22);

//        如果StringBuilder对象使用的缓冲区大于容纳其当前字符序列所需的容量
//        可以调用trimToSize()最小化用于字符的存储，去除未使用的空间(即缓冲区大小)
        System.out.println(str3.capacity() + " " + str3.length());
        System.out.println(str3);
        // trimToSize() 方法实际上是将缓冲区中额外大小删去 只留下存在字符串的空间
        str3.trimToSize();
        System.out.println(str3.capacity() + " " + str3.length());
        // StringBuffer 和 StringBuilder 没有运算符重载
//        str3 = str3 + "adfa";
        // StringBuilder 非线程安全，性能高，在单线程环境下使用
        StringBuilder str4 = new StringBuilder("asdfas");

        // capacity() 方法返回缓冲区大小 lenth() 方法返回实际存储字符串长度
        System.out.println(str4.capacity());
        str4.trimToSize();
        System.out.println(str4.capacity());

    }
}
