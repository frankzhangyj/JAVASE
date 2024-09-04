package com.microsoft.java8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Lambda 表达式实际上就是一个接口实例 用于简化接口中的单个方法实现
 *
 * Lambda 表达式通常用于实现函数式接口中的单个方法 函数式接口是指仅包含一个抽象方法的接口
 * 只能用于实现仅包含一个抽象方法的接口 例如Comparator Consumer Runnable接口中的唯一方法
 * 对于重写类或者接口中有多个方法 需要使用匿名内部类
 *
 * 四大内置函数式接口: Consumer<T> 有参无返回 void accept(T t)
 *                  Supplier<T> 无参有返回 T get()
 *                  Function<T, R> 有参有返回 R apply(T t)
 *                  Predicate<T> 有参返回为boolean boolean test(T t)
 *
 *  完整写法 (形参类型 形参列表) -> {重写的代码;} lambda 表达式代码块结尾没有; 参数类型可以省略
 */
public class LambdaTest1 {

    /**
     *无参无返回值
     */
    @Test
    public void test1(Runnable r1) {
        Runnable r2 = () -> {
            System.out.println("hello Lambda!");
        };

        r2.run();
    }

    /**
     * 有参有返回值 其中参数类型可以省略
     */
    @Test
    public void test2() {
        Comparator<Integer> com2 = (Integer o1, Integer o2) -> {
            return Integer.compare(o1, o2);
        };

        int compare2 = com2.compare(21, 12);
        System.out.println(compare2);
    }

    /**
     * 参数只有一个 参数小括号可以省略
     */
    @Test
    public void test3() {
        // ArrayList中的forEach方法参数是 Consumer 这是一个函数式接口 即只有一个抽象方法 accept
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");

        // 利用lambda表达式重写accept抽象方法实现输出集合中所有元素
        list.forEach(item -> {
            System.out.print(item + " ");
        });
    }

    /**
     * 当lambda方法体中只有一条语句 那么可以将 return 和 大括号 {} 省略
     */
    @Test
    public void test4() {
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);

        int res = com.compare(12, 21);
        System.out.println(res);

        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");

        // 重写 函数式接口Function中 apply 方法
        list.replaceAll(e -> e.toUpperCase());

        list.forEach(item -> System.out.print(item + " "));
    }
}
