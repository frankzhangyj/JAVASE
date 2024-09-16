package com.microsoft.generics.typeEnsure;

/**
 * 当泛型类或接口的子类重写泛型方法 在类型擦除后 为了避免多态和泛型的冲突 java会自动产生一个桥方法
 */
public class IntegerBox implements Box<Integer> {
    /**
     * 重写泛型接口中的方法 对象在运行时不会调用这个方法 所以java会自动生成一个桥方法
     * @param o 参数
     */
    @Override
    public void setBox(Integer o) {
        System.out.println("ss");
    }

    // 生成的桥方法
   /* public void setBox(Object o) {
        setBox((Integer)o);
    }*/
}
