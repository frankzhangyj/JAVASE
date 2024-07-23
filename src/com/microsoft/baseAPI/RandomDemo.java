package com.microsoft.baseAPI;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomDemo {
    public static void main(String[] args) {
        // Random类默认使用时间戳作为种子 也可以设置种子 所以是伪随机数
        Random ran = new Random();
        int num1 = ran.nextInt(10);
        System.out.println(num1);

        Random ran1 = new Random();
        // 生成0到1之间的随机数
        double num2 = ran1.nextDouble();
        System.out.println("num2 = " + num2);

        // Math类中的random方法底层也是创建一个Random类 然后调用nextDouble()方法 给出一个0到1之间的随机数
        System.out.println("Math.random() = " + Math.random());

        // ThreadLocalRandom是Random的子类，相对于Random的功能更全面和高效，同时线程安全，在使用的过程中也与时间戳有关，用于并发操作，current 表示当前线程
        double i = ThreadLocalRandom.current().nextDouble(0.9, 1.5);
        System.out.println("i = " + i);

    }
}
