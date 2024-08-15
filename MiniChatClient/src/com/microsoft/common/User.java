package com.microsoft.common;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 用户
 */
@Getter
@Setter
public class User implements Serializable {
    // 提高版本兼容性
    private static final long serialVersionUID = 1L;
    // 用户id
    private String id;
    // 用户密码
    private String pwd;

    public User() {}

    public User(String id, String pwd) {
        this.id = id;
        this.pwd = pwd;
    }
}
