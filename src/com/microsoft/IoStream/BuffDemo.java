package com.microsoft.IoStream;

import org.junit.Test;

import java.io.*;

public class BuffDemo {
    /**
     * 测试利用IOStream和buff数组实现文件复制
     * @throws IOException
     */
    @Test
    public void copyFileBase() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("test.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("test_2.txt");

        // 文件流的读取是一个一个字节读的，写也是一个一个字节写
        // 存储Input流中的字节 一次读取1024个字节 先将文件数据存放到内存中 然后一起输出到指定文件
        byte[] buff = new byte[1024];

        int copyTrans;

        while ((copyTrans = fileInputStream.read(buff)) != -1) {
            System.out.println(copyTrans);
            System.out.println(new String(buff, 0, copyTrans));
            // 将input流中字节存储到缓冲数组中 再将缓冲输出到目标文件中
            fileOutputStream.write(buff);
        }

        fileInputStream.close();
        fileOutputStream.close();
    }

    /**
     * buffered字节缓冲流实现文件复制
     * BufferedInputStream and BufferedOutputStream实现了缓冲功能的输入流/输出流
     * @throws IOException
     */
    @Test
    public void copyFile() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("test.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("test_3.txt");

        // 装饰者设计模式：动态地给一个对象添加额外的职责，就增加功能来说，装饰者模式相比生成子类更为灵活。
        //同时该模式也是继承关系的一种替代方法之一。
        //总之就是动态的给对象添加一些额外的职责，类似钢铁侠可以组装不同武器。

        // 缓冲输入流默认缓冲大小是8kb 一次读取8kb数据到缓冲输入流并存储到内存缓冲区中 磁盘扇区单位一块是4kb 文件系统一块是4kb或者更大
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        // 缓冲输出流将内存缓冲区数据输出
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);

        int copyTrans;
        // read返回一个字节对应的十进制
        while ((copyTrans = bufferedInputStream.read()) != -1) {
            // 将内存缓冲区数据输出
            System.out.println(copyTrans);
            bufferedOutputStream.write(copyTrans);
        }
        // 将缓冲区中的字节输出到流中
        bufferedOutputStream.flush();

        // 操作结束需要关闭缓冲流
        bufferedOutputStream.close();
        bufferedInputStream.close();
    }
}
