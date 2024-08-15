package com.microsoft.client.view;

import com.microsoft.client.utils.ScanUtils;

/**
 * 主菜单类
 */
public class MainMenu {
    private boolean loop = true;
    private String key;

    public void mainView() {
        while (loop) {
            System.out.println("==========欢迎登录网络通信系统==========");
            System.out.println("\t\t 1 登录系统");
            System.out.println("\t\t 9 退出系统");
            System.out.println("请输入你的选择: ");
            key = ScanUtils.scanString();
            switch (key) {
                case "1":
                    SecMenu secMenu = new SecMenu();
                    secMenu.secMenu();
                    break;
                case "9":
                    System.out.println("out");
                    loop = false;
                    break;
            }
        }
    }
}
