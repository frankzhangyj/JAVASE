package com.microsoft.generics;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 泛型都是引用类型（除了基本数据类型都是引用类型）
 * 泛型在编译后泛型擦除 自动将泛型替换为Object类
 * 对于主函数main中已经定义了的泛型 class文件中会保存 在之后自动进行类型转换
 */
public class CustomGenerics {
    public static void main(String[] args) {
        AA<String> stringC = new AA<String>();
        stringC.hi(0.7d, "nihao");
        // 两个泛型不同的对象不能相互转换
        /*ArrayList<String> al = new ArrayList<>();
        ArrayList<Object> al1 = al;*/
        List<Object> list = new ArrayList<>();
        List<String> list1 = new ArrayList<>();
        List<AA> list2 = new ArrayList<>();
        List<BB> list3 = new ArrayList<>();
        List<CC> list4 = new ArrayList<>();
    }

    /**
     * 注意使用pecs原则 避免类型兼容问题
     * 如果从集合中读取类型T 并且不能写入 那么使用 ? extends T 生产
     * 如果从集合中写入类型T 并且不能读取 那么使用 ? super T 消费
     * @param c
     */
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
