package com.microsoft.proxyPattern.dynamicProxy;

/**
 * 目标类
 */
public class BigStar implements Star {
    private String name;

    public BigStar() {

    }

    public BigStar(String name) {
        this.name = name;
    }


    @Override
    public String sing(String song) {
        System.out.println(this.name + "开始唱歌: " + song);
        return "thank you";
    }

    @Override
    public void dance() {
        System.out.println(this.name + "开始跳舞");
    }
}
