package com.microsoft.java8;

import org.junit.Test;

import java.util.function.Function;

/**
 * 构造器引用和方法引用类似 函数式接口抽象方法的参数列表返回值类型和构造器一致
 * 抽象方法的返回值类型就是构造器所属类的类型
 *
 * 数组引用 可以将数组看作是一个特殊的类 写法和构造器引用一致
 */
public class ConstructorAndArrayReference {
    /**
     * 构造器引用
     */
    @Test
    public void test1() {
        Function<String, Employee2> fun = s -> new Employee2(s);
        // 因为Employee2中有一个构造方法是 Employee2(String name)
        // R apply(T t)  Employee2(String name) 返回值和参数都相同所以可以使用构造器引用
        Function<String, Employee2> fun1 = Employee2::new;
    }

    /**
     * 数组引用
     */
    @Test
    public void test2() {
        Function<Integer, String[]> func = length -> new String[length];

        Function<Integer, String[]> func1 = String[] :: new;
    }
}
