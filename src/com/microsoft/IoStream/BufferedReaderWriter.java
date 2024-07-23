package com.microsoft.IoStream;

import org.junit.Test;

import java.io.*;

public class BufferedReaderWriter {
    // BufferedReader and BufferedWriter 高级字符流 通过内部缓冲区减少了实际的I/O操作次数，效率更高。
    // 适合于处理大量字符的文本文件
    @Test
    public void BufferedReaderTest() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("test.txt"));
        String poetry;
        while ((poetry = bufferedReader.readLine()) != null) {
            System.out.println(poetry);
        }
        bufferedReader.close();
    }

    @Test
    public void BufferedWriterTest() throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("test_3.txt"));
        bufferedWriter.write("fffff");
        bufferedWriter.newLine();
        bufferedWriter.write("you");
        // 一定要将字符刷新（从缓冲区输出到流中）
        bufferedWriter.flush();
        // 如果没关，写出来的文件中不会有内容显示
        bufferedWriter.close();
    }
}
