package com.microsoft.baseAPI;

import java.util.Scanner;

public class ScannerDemo {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        // hasNext、hasNextLine在返回一个boolean类型结果true的同时，
        // 会在堆空间中开辟一块专门用于存放刚刚输入的字符串，用于下次next或者nextLine：
        System.out.println("next方式接收：");
        // hasNext 在输入时遇到空格或者回车会自动截断 不会返回false直到返回true 发生阻塞
        if (scan.hasNext()) {
            String str1 = scan.next();
            System.out.println("数据为：" + str1);
        }
        // hasNextLine 只有遇到回车才会停止 也不会返回false
        if (scan.hasNextLine()) {
            String str2 = scan.nextLine();
            System.out.println("数据为：" + str2);
        }

        // 对于输入int double byte float short long 原理同next
        scan.close();
    }
}
