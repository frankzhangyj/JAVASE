package com.microsoft.java8;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.junit.Test;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 方法引用本质上是一个 lambda 表达式 而 lambda 表达式作为函数式接口的实例，所以方法引用也是函数式接口的实例
 * 就相当于lambda表达式方法体中有一个调用方法的语句 将这个lambda表达式简化
 * <p>
 * void accept(T t) 那么方法引用也必须是 void function(T t)
 * 使用格式: 类(或对象) :: 方法名
 * <p>
 * 当 lambda 表达式的方法体中已经有实现方法了 并且接口中的抽象方法形参列表和返回值类型和引用方法的相同
 * 对象 :: 非静态方法
 * 类 :: 静态方法
 * <p>
 * <p>
 * 类 :: 非静态方法
 */
public class MethodReference {

    /**
     * 对象 :: 非静态方法
     * lambda表达式方法体中 void println(T t) 已经有实现体
     * 所以可以将其替换为方法引用 对象 :: 方法名 (参数就是 lambda 表达式的参数即函数式接口的参数)
     */
    @Test
    public void test1() {
        // void accept(T t)  void println(T t)

        Consumer<String> con = str -> System.out.println(str);

        PrintStream ps = System.out;

        Consumer<String> con1 = ps::println;

        // T get()   String getName()

        Supplier<String> sup = () -> "fuck";

        Employee2 t = new Employee2("fuck");

        Supplier<String> sup2 = t::getName;
    }

    /**
     * 类 :: 静态方法
     */
    @Test
    public void Employee2() {
        // int compare(T t1, T t2)  Integer: int compare(T t1, T t2)

        Comparator<Integer> com = (o1, o2) -> Integer.compare(o1, o2);

        Comparator<Integer> com1 = Integer::compare;

        // Long apply(Double d)  Math: Long round(Double d)

        Function<Double, Long> fun = d -> Math.round(d);

        Function<Double, Long> fun1 = Math::round;
    }

    /**
     * 类 :: 非静态方法
     */
    @Test
    public void test3() {
        // s1.compareTo(s2) 其中一个参数调用方法 第一个参数类型::非静态方法
        // s1.equals(s2)  String(s1的类型)::非静态方法

        Comparator<String> com = (s1, s2) -> s1.compareTo(s2);

        Comparator<String> com1 = String::compareTo;

        BiPredicate<String, String> bi = (s1, s2) -> s1.equals(s2);

        BiPredicate<String, String> bi1 = String::equals;

        Employee2 employee2 = new Employee2("jack");

        // R apply(T t)  参数调用 String getName()  参数类型::getName()
        Function<Employee2, String> fun = Employee2 -> Employee2.getName();
        System.out.println(fun.apply(employee2));
        Function<Employee2, String> fun1 = Employee2::getName;
        System.out.println(fun.apply(employee2));
    }
}

@Setter
@Getter
@ToString
class Employee2 {
    private int Salary;
    private String name;

    public Employee2() {

    }

    public Employee2(String name) {
        this.name = name;
    }
}

@Getter
@Setter
class Employee2Data {
    private static ArrayList<Employee2> employee2s;

    public Employee2Data() {

    }

    public static List<Employee2> getList() {
        return employee2s;
    }

    public void addList(Employee2 e) {
        this.employee2s.add(e);
    }
}
