package com.microsoft.proxyPattern.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Random;

/**
 * 通过动态代理跟踪在二分查找过程中 key 的调用情况
 */
public class BinarySearchProxy {
    public static void main(String[] args) {
        // 代理数组
        Object[] objects = new Object[1000];

        for (int i = 0; i < objects.length; i++) {
            Integer value = i + 1;
            TraceHandler traceHandler = new TraceHandler(value);
            // 创建一个代理类
            Object proxy = Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                    new Class[] {Comparable.class}, traceHandler);
            objects[i] = proxy;
        }

        Integer key = new Random().nextInt(objects.length) + 1;

        int result = Arrays.binarySearch(objects, key);

        if (result >= 0) System.out.println(objects[result]);
    }
}

/**
 * 实现调用处理器的 invoke 方法 具体为 midVal.compareTo(key);
 * target 为 midVal(也就是代理数组中元素存储的值) 内部是proxy.compareTo(key)
 * args[] 实际上只有一个值 key
 */
class TraceHandler implements InvocationHandler {
    private Object target;

    public TraceHandler(Object target) {
        this.target = target;
    }

    /**
     * 实现调用处理器的invoke方法 可以打印给定代理类中调用具体方法的实例值 和 参数值
     * 在这里打印在Arrays.binarySearch() 中midVal.compareTo(key);
     * @param proxy the proxy instance that the method was invoked on
     *
     * @param method the {@code Method} instance corresponding to
     * the interface method invoked on the proxy instance.  The declaring
     * class of the {@code Method} object will be the interface that
     * the method was declared in, which may be a superinterface of the
     * proxy interface that the proxy class inherits the method through.
     *
     * @param args an array of objects containing the values of the
     * arguments passed in the method invocation on the proxy instance,
     * or {@code null} if interface method takes no arguments.
     * Arguments of primitive types are wrapped in instances of the
     * appropriate primitive wrapper class, such as
     * {@code java.lang.Integer} or {@code java.lang.Boolean}.
     *
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.print(this.target);
        System.out.print("." + method.getName() + "(");

        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                System.out.print(args[i]);
                if (i < args.length - 1) {
                    System.out.print(", ");
                }
            }
        }
        System.out.println(")");

        return method.invoke(target, args);
    }
}
