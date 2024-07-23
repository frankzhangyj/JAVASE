package com.microsoft.IoStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class FileInputDemo {
    public static void main(String[] args) {
        // 通过创建一个File对象对文件的链接实现FileInputStream对象的创建
        File file = new File("D:\\porject\\test.txt");

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            StringBuffer stringBuffer = new StringBuffer();
            int inPut = 0;
            while (true) {
                // 将文件信息输入到程序中 （站在程序的角度是输入）
                // read每次读取一个字节 然后转换为十进制int返回
                inPut = fileInputStream.read();
                char c = (char)inPut;
                if (inPut == -1) break;
                stringBuffer.append(c);
            }
            System.out.println(stringBuffer);
            // 代码最后加上close关闭输入流与文件的链接
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("not found file");
        } catch (IOException e) {
            System.out.println("IO exception");
        }

        // 也可以直接new FileInputStream("文件路径") 来创建 底层同样是先创建一个File对象
        byte[] bytes = new byte[2];
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            int input = 0;
            int fre = 0;
            while (true) {
                // read 返回读取字节长度
                input = fileInputStream.read(bytes);
                System.out.println(input);
                if (input == -1) break;
                System.out.println(Arrays.toString(bytes)); //读取后的字节数组内容
                fre++;
                System.out.println("执行次数："+fre);
            }
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("not found file");
        } catch (IOException e) {
            System.out.println("IO exception");
        }

    }
}
