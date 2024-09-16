package com.microsoft.throwable;

public class ThrowableDemo {
    // Throwable 有两个子类 Erro 和 Exception
    // Exception 子类 IOException 和 RunTimeException 等等
    // Erro类 和 RuntimeException类 都是非检查型异常 其他都是检查型异常
    // RuntimeException 可以不用处理(程序员可以通过修改代码处理异常) 但是非运行时异常（编译异常）就必须捕获
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

    // throws 关键字用来声明方法中可能出现的异常类型 (用于传递方法中throw的异常到该方法的调用者处理)
    // throw 关键字用来抛出异常 try catch 用来捕获处理异常 两者结合效果更好
    // 对于检查型异常 必须使用 throws 声明异常 调用这处理这个异常
    // 对于非检查型异常(RuntimeException)如果没有声明 throws 那么调用者不会捕获这异常
    public static int sum(int num1, int num2) throws IllegalArgumentException {
        if (num1 < 0 && num2 < 0) {
            throw new IllegalArgumentException("参数小于0");
//            IllegalArgumentException illegalArgumentException = new IllegalArgumentException("参数小于0");
//            throw illegalArgumentException;
        }
        return  num1 + num2;
    }
}
