package com.microsoft.generics.demo;

/**
 * 泛型类型在编译时会擦除 所有的 T 会自动转换为Object 或者 上限
 * 运行时 因为已经经过编译时的类型检查 所以在运行时利用记录的类型信息进行适当转换
 */
public class Test {
    public static void main(String[] args) {
        AClass<Integer> a = new AClass();
        int sum = 1 +  a.returnFun(Integer.valueOf(4));
        System.out.println(sum);
    }
}

class AClass<T> {
    public T returnFun(T a) {
        return a;
    }
}
