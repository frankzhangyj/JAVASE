package com.microsoft.IoStream;

import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputDemo {
    @Test
    public void ouputFile() throws IOException {
        // 创建一个输出流将程序数据写入文件中
        FileOutputStream fileOutputStream = new FileOutputStream("test.txt");
        // 将字符串转换为字节数组
        byte[] bytes = "HelloWorld".getBytes();
        // 将字节数组写入文件中
        fileOutputStream.write(bytes);
        // 也可以通过循环将bytes写入文件中
//        for (int i = 0; i < bytes.length; i++) {
//            fileOutputStream.write(bytes[i]);
//        }
        fileOutputStream.close();
    }
}
