package com.microsoft.java8;

import org.junit.Test;

import java.util.Optional;

public class OptionalDemo {
    /**
     * 实质上也是一个容器 可以存储空值对象 避免空指针异常
     */
    @Test
    public void test1() {
        // of()方法创建的参数对象不能为空
        Employee2 employee2 = new Employee2();
        Optional<Employee2> employee21 = Optional.of(employee2);
        System.out.println(employee21);
        // ofNullable()方法参数对象可以为空
        employee2 = null;
        Optional<Employee2> employee22 = Optional.ofNullable(employee2);
        System.out.println(employee22);
    }

    /**
     * 利用orElse() 方法设置一个备选值 避免空指针异常
     */
    @Test
    public void test2() {
        Employee2 employee2 = null;
        Optional<Employee2> employee21 = Optional.ofNullable(employee2);
        // 通过设置一个备选值 可以避免空指针异常
        Employee2 employee22 = employee21.orElse(new Employee2("fuck"));
        System.out.println(employee22);
    }
}
