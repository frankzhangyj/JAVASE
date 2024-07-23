package com.microsoft.reflection.app;

import com.microsoft.reflection.Cat;

import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * java 在计算机中的三个阶段：
 * 编译（.java -> .class）
 * 加载（.class -> Class类对象(堆中) and 字节码二进制数据(方法区) ）
 * 1.加载：通过类加载器classLoader类中loadClass() 加载到内存中 且同一个类只加载一次
 * 2.连接：验证（确保.class文件不会危害虚拟机 可以将其关闭） 准备（对静态变量分配内存并初始化） 解析 (将符号引用转换为符号引用)
 * 3.初始化：执行<clint>()方法 将静态变量和方法按顺序合并 在多线程下会进行加锁
 * 运行（new 对象）
 * <p>
 * 正：                                                new object                       object.method()
 * 反射中包括：Class类（操作实例的对象） Method类（操作实例方法的对象） Filed类（操作实例属性的对象） Constructor类（操作构造函数的对象） 等
 * 反：Class cls = Class.forname("object address")     Object o = cls.newInstance()     method.invoke(o)
 * <p>
 * 反射可以动态的创建和使用对象（框架底层的核心） 但是反射基本是解释执行 对执行速度有影响
 */
public class Run {
    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src//file.properties"));
        String classPath = properties.get("classPath").toString();
        String method = properties.get("method").toString();

        // 获取类的 Class 对象实例
        Class cls = Class.forName(classPath);

        // 获取已知对象的Class实例
        // Class clz = String.class;

        // 使用类对象的getClass()方法
        // String str = new String("Hello");
        // Class clz = str.getClass();


        // 获取 Class类对象构造器的 Constructor 对象
        Constructor constructor = cls.getConstructor(String.class);

        // 使用 Constructor 对象的 newInstance 方法获取反射类对象(可以使用有参构造函数获取反射类对象)
        //Object o = constructor.newInstance("dog");

        // 使用Class 对象获取反射类对象（默认调用无参构造函数）
        Object o = cls.newInstance();

        // 利用setAccessible(true) 使用类的私有构造器创建实例
        Constructor declaredConstructor = cls.getDeclaredConstructor(String.class, int.class);
        declaredConstructor.setAccessible(true);
        Object o1 = declaredConstructor.newInstance("haha", 20);
        System.out.println("privateConstructor" + " :" + o1.toString());

        // 获取Class类对象方法的 Method 对象
        Method method1 = cls.getMethod(method);

        // 通过setAccessible(true)调用私有方法
        Method sleep = cls.getDeclaredMethod("sleep", int.class);
        sleep.setAccessible(true);
        sleep.invoke(o, 10);

        // 利用 invoke 方法调用方法
        method1.invoke(o);

        // 获取Class类中的所有公有属性
        System.out.println("public");
        Field[] fields = cls.getFields();
        for (Field field : fields) {
            System.out.println(field.getName() + " " + field.getType());
        }
        // 获取到所有的属性 包括私有属性(获取方法和构造器也类似 可以用declared获取私有)
        System.out.println("private + public");
        Field[] fields1 = cls.getDeclaredFields();
        for (Field field : fields1) {
            System.out.println(field.getName());
        }

        // 通过setAccessible(true) 修改私有属性
        Field field = cls.getDeclaredField("name");
        field.setAccessible(true);
        field.set(o, "shit");

        // 属性对象调用get()方法 得到具体实例对象的属性值
        System.out.println(fields[0].get(o));

        // 反射速度慢 可以通过将Method Field Constructor中的setAccessible(true) 将安全检查关闭
        met1();
        met2();
        met3();
    }

    /**
     * 正常创建对象 调用方法
     */
    public static void met1() {
        Cat cat = new Cat();
        long st = System.currentTimeMillis();
        for (int i = 0; i < 90000000; i++) {
            cat.bark();
        }
        long ed = System.currentTimeMillis();
        System.out.println(ed - st);
    }

    /**
     * 反射创建Class对象 创建实例 创建Method对象 调用实例的方法
     *
     * @throws Exception
     */
    public static void met2() throws Exception {
        Class aClass = Class.forName("com.microsoft.reflection.Cat");
        Object o = aClass.newInstance();
        Method bark = aClass.getMethod("bark");

        long st = System.currentTimeMillis();
        for (int i = 0; i < 90000000; i++) {
            bark.invoke(o);
        }
        long ed = System.currentTimeMillis();
        System.out.println(ed - st);
    }

    /**
     * 速度优化 setAccessible(true)
     *
     * @throws Exception
     */
    public static void met3() throws Exception {
        Class aClass = Class.forName("com.microsoft.reflection.Cat");
        Object o = aClass.newInstance();
        Method bark = aClass.getMethod("bark");
        bark.setAccessible(true);

        long st = System.currentTimeMillis();
        for (int i = 0; i < 90000000; i++) {
            bark.invoke(o);
        }
        long ed = System.currentTimeMillis();
        System.out.println(ed - st);
    }

}
