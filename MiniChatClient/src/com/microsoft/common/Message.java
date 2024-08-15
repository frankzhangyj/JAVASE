package com.microsoft.common;

import lombok.Getter;
import lombok.Setter;
import org.junit.Test;

import java.io.Serializable;

/**
 * 信息类 用于客户端与服务端通过socket通道连接发送信息
 */
@Getter
@Setter
public class Message implements Serializable {
    // 用ObjectOutPutStream 传输的类需要序列化
    private static final long serialVersionUID = 1L;
    private String sender;
    private String receiver;
    private String content;
    private String sendAdd;
    private String receAdd;
    private byte[] file;
    private String sendTime;
    private String mesType;
}
