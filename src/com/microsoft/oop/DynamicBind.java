package com.microsoft.oop;

public class DynamicBind {
    public static void main(String[] args) {
        // 多态动态绑定机制
        // 向上转型 父类引用变量 引用 子类对象地址
        // a 的编译类型是A 运行类型是B 即a不能调用B中的say方法 因为编译时找不到(静态绑定 编译时方法调用与方法实现的连接)
        A a = new B();
        // 在调用方法或者属性时先子类 子类没有去父类
        System.out.println(a.sum()); // 20
        // 动态绑定(法调用在运行时与方法实现的连接)：
        // 当调用对象方法时，该方法会和该对象的运行类型绑定
        // 当调用对象属性时，没有动态绑定机制，哪里声明哪里使用
        // (继承性质：在调用方法或者属性时先子类 子类没有去父类)
        System.out.println(a.sum1()); // 30
    }
}

class A {
    int i = 10;

    public int sum() {
        return i + 10;
    }

    public int sum1() {
        return get() + 10;
    }

    public int get() {
        return i;
    }
}

class B extends A {
    int i = 20;

//    public int sum() {
//        return i + 10;
//    }

    public int get() {
        return i;
    }

    public void say() {

    }
}
