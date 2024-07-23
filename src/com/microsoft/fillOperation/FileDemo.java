package com.microsoft.fillOperation;

import java.io.File;

public class FileDemo {
    public static void main(String[] args) {
        // 创建一个file对象需要使用绝对路径absolute path 并且\需要转义为\\
        File file = new File("D:\\porject\\javaAPI");
        System.out.println(file.getParentFile());
        System.out.println(file.getPath());
        System.out.println(file.getName());
        // 遍历目录下的所有文件
        String[] list = file.list();
        for (String s : list) {
            System.out.println(s);
        }

    }
}
