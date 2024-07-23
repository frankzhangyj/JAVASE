package com.microsoft.net;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

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
