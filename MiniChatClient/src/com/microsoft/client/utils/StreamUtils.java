package com.microsoft.client.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 流转化类 便于字节流的转化
 */
public class StreamUtils {
    public static byte[] streamToByteArray(InputStream in) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buff = new byte[1024];
        int len = 0;
        while ((len = in.read(buff)) != -1) {
            byteArrayOutputStream.write(buff, 0, len);
        }
        byte[] array = byteArrayOutputStream.toByteArray();
        return array;
    }

    /**
     * 将字符流转换为字符
     * @param in 输入流
     * @return
     * @throws Exception
     */
    public static String streamToString(InputStream in) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
        StringBuffer ans = new StringBuffer();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            ans.append(line + "\r\n");
        }
        return ans.toString();
    }
}
