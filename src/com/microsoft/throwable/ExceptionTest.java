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

    enum Weekday {
        SUN(), MON, TUE, WED, THU, FRI, SAT;
    }

// 编译后的枚举类 本质也是一个类 不可继承 不可构造 实例字段唯一不可变
//    判断枚举常量的名字必须使用name() 不能使用toString()
//    final class Weekday extends Enum {
//        private final Weekday SUN = new Weekday();
//        ...
//
//        private Weekday(){}
//    }
    /**
     * 不同枚举类型不能比较
     * 同一个枚举类型中的值可以相互比较 每个常量在JVM中只有一个唯一实例
     */
    @Test
    public void testEquals() {
        System.out.println(Weekday.SUN == Weekday.SUN);

    }
}
