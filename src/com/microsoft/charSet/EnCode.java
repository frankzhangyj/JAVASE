package com.microsoft.charSet;

import org.junit.Test;

import java.io.IOException;

public class EnCode {
    @Test
    public void enCodeTest() throws IOException {
        String c = "啊";
        // unicode 是国际字符码 包括了几乎所有的字符
        // UTF-8 是unicode 的编码形式的其中一种 使用广泛
        // gbk 涵盖了ascii 和 gb2312
        byte[] bytes = c.getBytes("gbk");
        StringBuffer sb = new StringBuffer();
        if (bytes != null && bytes.length > 0)
        {
            for (int i = 0; i < bytes.length; i++) {
                String hex = Integer.toHexString(bytes[i]);
                System.out.println(hex + "\n");
                sb.append(hex);
            }
        }
    }
}
