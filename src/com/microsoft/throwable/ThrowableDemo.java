package com.microsoft.throwable;

public class ThrowableDemo {
    // Throwable 有两个子类 Erro 和 Exception
    // Exception 子类 IOException 和 RunTimeException 等等
    // 运行时异常可以不用处理 但是非运行时异常（编译异常）就必须捕获
    public static void main(String[] args) {
        // finally 表示不论是否捕获成功 都会执行 使用try catch可以不搭配finally 但是使用finally就必须搭配try catch
        try {
            sum(-2,-2);
        } catch (Exception e) {
            // 显示异常的结果和定位异常 也可以显示
            e.printStackTrace();
        } finally {
            System.out.println("捕获成功！");
        }
        System.out.println(sum(-1, -1));
    }

    // throws 关键字用来声明方法中可能出现的异常类型
    // throw 关键字用来抛出异常 try catch 用来捕获处理异常 两者结合效果更好
    public static int sum(int num1, int num2) throws IllegalArgumentException {
        if (num1 < 0 && num2 < 0) {
            throw new IllegalArgumentException("参数小于0");
//            IllegalArgumentException illegalArgumentException = new IllegalArgumentException("参数小于0");
//            throw illegalArgumentException;
        }
        return  num1 + num2;
    }
}
