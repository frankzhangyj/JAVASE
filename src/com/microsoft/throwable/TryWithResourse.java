package com.microsoft.throwable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.Charset;

public class TryWithResourse {

    public static void test2() throws Exception {
        String filePath = "D:\\test1\\test1.txt";
        // java7中的try-with-resource，java9中有所改善
        // 在try进行后自动关闭资源
        try (FileOutputStream fileOutputStream = new FileOutputStream(new File(filePath));
             FileInputStream fileInputStream = new FileInputStream(new File(filePath))) {
            // 写
            fileOutputStream.write("ab1024".getBytes(
                    Charset.defaultCharset()));
            // 读取
            int data = fileInputStream.read();
            while (data != -1) {
                System.out.print((char) data);
                data = fileInputStream.read();
            }
            // 模拟异常
            //  int a = 0 / 0;
        }

    }
}
