package com.microsoft.reflection;

public class GetClassdemo {
    /**
     * 不论用什么方式获取Class类对象 同一个类加载到内存只有一个 获取到的Class类对象是同一个
     * @param args
     * @throws ClassNotFoundException
     */
    public static void main(String[] args) throws ClassNotFoundException {
        // 用于配置文件 读取类全路径
        String classPath = "com.microsoft.reflection.Cat";
        Class aClass = Class.forName(classPath);
        System.out.println(aClass);

        // 已知具体的类 获取其Class类对象 用于反射中方法参数传递
        System.out.println(Cat.class);

        // 已知具体实例 获取其Class类对象
        Cat cat = new Cat();
        System.out.println(cat.getClass());

        // 通过类加载器获取其Class类对象
        ClassLoader classLoader = Cat.class.getClassLoader();
        Class aClass1 = classLoader.loadClass(classPath);
        System.out.println(aClass1);


    }

}
