package com.microsoft.IoStream;

import org.junit.Test;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileReaderWriter {
    // FileReader and FileWriter 适合对txt文本文件的处理 基础字符流 直接对文件操作
    // 不需要再设置字节数组来进行读取，在写文件的时候也不需要获取字符串的字节流
    @Test
    public void ReaderTest() throws IOException {
        // 按字符读取流中数据
        FileReader reader = new FileReader("test.txt");
        int poetry;
        while ((poetry = reader.read()) != -1) {
            System.out.println((char)poetry);
        }
        reader.close();
    }

    @Test
    public void WriterTest() throws IOException {
        // 按字符向流中写入数据
        FileWriter writer = new FileWriter("demo_1.txt");

        writer.write("fuck you");
        writer.close();
    }
}
