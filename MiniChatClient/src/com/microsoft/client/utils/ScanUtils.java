package com.microsoft.client.utils;

import java.util.Scanner;

/**
 * 输入类 便于用户输入
 */
public class ScanUtils {
    /**
     * 将用户输入的数字转换为字符串
     * @return
     */
    public static String scanString() {
        String res = "";
        Scanner scanner = new Scanner(System.in);
        res = String.valueOf(scanner.nextInt());
        return res;
    }

    /**
     * 得到用户输入的字符串
     * @return
     */
    public static String scanContent() {
        String res = "";
        Scanner scanner = new Scanner(System.in);
        res = scanner.nextLine();
        return res;
    }
}
