package com.microsoft.Enum;

import org.junit.Test;

/**
 * 枚举实际上也是一个类 继承了Enum 不可被继承 不可构造 实例字段唯一不可变 枚举不是一个数据类型
 * 比如在编译后：
 * //    final class Weekday extends Enum {
 * //        public static finall Weekday SUN = new Weekday(1, "周天");
 * //        ...
 * //
 * //        private Weekday(int days, String mess){}
 * //    }
 */
public enum WeekDay {
    SUN(0, "周天"),
    MON(1, "周一"),
    TUE(2, "周二"),
    WED(3, "周三"),
    THU(4, "周四"),
    FRI(5, "周五"),
    SAT(6, "周六");

    private final int days;
    private final String mess;

    WeekDay(int day, String mess) {
        this.days = day;
        this.mess = mess;
    }

    public int getDays() {
        return this.days;
    }

    public String getMess() {
        return this.mess;
    }

    public static void main(String ... args) {
        // 不同枚举类型不能比较
        // 同一个枚举类型中的值可以相互比较 每个常量在JVM中只有一个唯一实例
        // 判断枚举常量的名字必须使用name() 不能使用toString()
        System.out.println(SAT.name() == WED.name());
        System.out.println(SAT.toString());
        System.out.println(SAT.getDays() == WED.getDays());
    }
}
