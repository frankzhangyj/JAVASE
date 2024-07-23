package com.microsoft.throwable;

import org.junit.Test;

public class ExceptionTest {
    // 抛出自定义异常
    public int sum(int num1, int num2) throws MyException {
        if (num1 > 10 || num2 > 10 || num1 < 0 || num2 < 0) {
            // 装b写法 通过一个枚举类型将所有可能异常列出 然后通过枚举值抛出code和msg
            throw new MyException(MyCodeEnum.NOT_O_TEN);
        }
        return num1 + num2;
    }

    @Test
    public void test() throws MyException {
        // 捕获自定义异常
        try {
            sum(100, 100);
        } catch (MyException e) {
            e.printStackTrace();
        } finally {
            System.out.println("yes");
        }
        //sum(100, 100);
    }
}
