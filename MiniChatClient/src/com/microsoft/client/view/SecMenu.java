package com.microsoft.client.view;

import com.microsoft.client.service.ClientCommunicateService;
import com.microsoft.client.service.ClientService;
import com.microsoft.client.utils.ScanUtils;
import com.microsoft.common.User;

/**
 *二级菜单类
 */
public class SecMenu {
    private String userId;
    private String pwd;
    private boolean loop1 = true;
    private boolean loop2 = true;
    private String key;
    // 用户服务
    private ClientService clientService = new ClientService();
    // 用户聊天服务
    private ClientCommunicateService clientCommunicateService = new ClientCommunicateService();
    private User user = new User();

    /**
     * 创建一个二级菜单 用户根据提示操作
     */
    public void secMenu() {

        while (loop1) {
            System.out.print("请输入帐号: ");
            userId = ScanUtils.scanString();

            System.out.print("请输入密码: ");
            pwd = ScanUtils.scanString();

            user.setId(userId);
            user.setPwd(pwd);
            // 检查用户是否存在
            if (clientService.checkUser(user)) {
                clientCommunicateService.setUserId(userId);
                // 加载离线消息
                clientCommunicateService.loadOfflineMessage();
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                while (loop2) {
                    System.out.println("==========网络通信系统二级菜单(用户 " + userId + " )==========");
                    System.out.println("\t\t 1 显示在线用户列表");
                    System.out.println("\t\t 2 群发消息");
                    System.out.println("\t\t 3 私聊消息");
                    System.out.println("\t\t 4 发送文件");
                    System.out.println("\t\t 9 退出系统");
                    System.out.println("请输入你的选择: ");

                    key = ScanUtils.scanString();

                    switch (key) {
                        case "1":
                            clientService.onlineFriendList();
                            // 网络通信存在延迟 避免菜单线程先显示出来 所以将菜单线程推迟一些时间
                            try {
                                Thread.sleep(5);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            break;
                        case "2":
                            clientCommunicateService.groupChatSend();
                            break;
                        case "3":
                            clientCommunicateService.privateChatsend();
                            break;
                        case "4":
                            clientCommunicateService.fileSend();
                            break;
                        case "9":
                            clientService.logout();
                            loop1 = false;
                            loop2 = false;
                            break;
                    }
                }
            }
        }
    }

}
