package com.microsoft.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型都是引用类型（除了基本数据类型都是引用类型）
 */
public class CustomGenerics {
    public static void main(String[] args) {
        AA<String> stringC = new AA<String>();
        stringC.hi(0.7d, "nihao");

        List<Object> list = new ArrayList<>();
        List<String> list1 = new ArrayList<>();
        List<AA> list2 = new ArrayList<>();
        List<BB> list3 = new ArrayList<>();
        List<CC> list4 = new ArrayList<>();
    }

    // 在泛型中 ? 是通配符 单独使用表示任意泛型
    public static void printCollection(List<?> c) {
    }

    // ? extends AA 表示该泛型只能是AA 或者是AA的子类
    public static void printCollection1(List<? extends AA> c) {
    }

    // ? super CC 表示该泛型只能是CC 或者是CC的父类
    public static void printCollection2(List<? super CC> c) {
    }
}

interface A<T> {
}

// 接口继承时需要确定父类接口的泛型类型 默认为Object
interface B<E> extends A<E> {
}

// 类实现接口需要确定接口泛型类型
class AA<T> implements A<Double> {
    // 泛型方法
    public <K, V> void hi(K a, T t) {
        System.out.println(a.getClass());
        System.out.println(t.getClass());
    }
}

class BB extends AA {
}

class CC extends BB {
}
