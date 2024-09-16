package com.microsoft.generics.typeEnsure;

public class Main {
    public static void main(String[] args) {
        // 在编译时 会将Box中的泛型Integer擦除变为Object
        Box<Integer> integerBox = new IntegerBox();
        // 静态绑定不会出现问题 因为Box<Integer>中存在这个方法setBox(Integer) 此时10还是会当作Integer
        // 动态绑定后 由于类型擦除 此时这个方法参数10就会成为Object类型 所以需要调用setBox(Object) 方法
        // 但是IntegerBox类中没有这个Object参数方法 所以java会自动生成桥方法
        integerBox.setBox(10);
    }
}
