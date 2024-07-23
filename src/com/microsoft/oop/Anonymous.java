package com.microsoft.oop;

public class Anonymous {
    /**
     * 匿名内部类只能重写接口 或者类中已经存在的方法 不能创建一个新方法
     * 匿名内部类只能访问父类中public protected属性 可以访问外部类的所有属性和方法 （相当于类的继承）
     * @param args
     */

    public static int commenNum = 10;
    public static void main(String[] args) {
        // 匿名内部类可以简化开发 避免实现接口必须创建一个类 但这个类只使用一次
        // 实际上jvm 会在底层创建一个类实现这个接口 类名是当前类名$1 但是这个类只能创建一个实例
        // 编译类型是AAA 运行类型是匿名内部类
        AAA a = new AAA() {
            public int commenNum = 20;
            @Override
            public void speak() {
                System.out.println("fuck" + Anonymous.commenNum);
            }
        };

        a.speak();
        System.out.println(a.getClass());

        // 对于实现一个类的匿名内部类 底层会创建一个类继承这个类 类名是当前类名$2
        // 只能重写已经存在的方法 且不能访问private属性 调用原类的构造函数
        AA aa = new AA("jack") {
            @Override
            public void cry() {
                System.out.println(name + " cry..");
            }
        };

        aa.cry();

        // 也可以直接利用匿名内部类调用方法
        new AA("frank") {
            @Override
            public void cry() {
                System.out.println(name + " not cry..");
            }
        }.cry();

        // 也可以当作形参传递
        AA aa1 = new AA();
        // 这里的参数是匿名内部类 实现了接口 并且是参数
        aa1.sleep(new AAA() {
            @Override
            public void speak() {
                System.out.println("fuck you");
            }
        });
    }
}

class AA {
    public String name;

    public AA() {}

    public AA(String name) {
        this.name = name;
        System.out.println("constructor");
    }

    public void cry() {
        System.out.println(this.name + "cry");
    }

    public void sleep(AAA aaa) {
        aaa.speak();
    }
}

interface AAA {
    void speak();
}
