package com.microsoft.baseAPI;

public class MathDemo {
    public static void main(String[] args) {
        // Math中的方法都是static类型 可以之间Math.调用
        // round方法实质是Math.floor(x + 0.5);
        int a = Math.round(11.5f);
        System.out.println("a = " + a);
        int b = Math.round(-11.5f);
        System.out.println("b = " + b);
    }


}
