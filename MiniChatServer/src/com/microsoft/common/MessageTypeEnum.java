package com.microsoft.common;

/**
 * 定义Message的传输类型
 */
public enum MessageTypeEnum {
    LOGIN_SUCCESS("1", "登录成功"),
    LOGIN_FAIL("2", "登录失败"),
    MESSAGE_GET_ONLINE_FRIEND("3", "请求在线用户列表"),
    MESSAGE_RETURN_ONLINE_FRIEND("4", "返回在线用户列表"),
    MESSAGE_COMMON_MES("5", "普通消息"),
    MESSAGE_CLIENT_EXIT("6", "客户端退出"),
    MESSAGE_GROUP_MES("7", "群聊消息"),
    MESSAGE_FILE_MES("8", "文件类"),
    MESSAGE_LOAD_MES("9", "加载离线消息")
    ;

    private final String code;
    private final String description;

    MessageTypeEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
