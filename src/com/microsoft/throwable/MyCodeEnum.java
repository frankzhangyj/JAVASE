package com.microsoft.throwable;

public enum MyCodeEnum implements ErrorCode {
    // 枚举代替了常量类
    NOT_FOUND_PAGE("404", "找不到网站资源"),
    NOT_FOUND_FILE("888", "找不到文件"),
    NOT_O_TEN("bad", "只能求10以内的加法")
    ;

    MyCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    // 枚举变量顺序就是枚举中值的顺序
    private final String code;
    private final String msg;

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }
}
