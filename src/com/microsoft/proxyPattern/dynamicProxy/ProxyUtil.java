package com.microsoft.proxyPattern.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 构造代理工具类
 */
public class ProxyUtil {
    /**
     * 用于构造一个Star类型的动态代理对象
     * @param bigStar 代理目标对象
     * @return 代理对象
     */
    public static Star createStar(BigStar bigStar) {
        // param1 用于指定当前代理工具类的类加载器
        // param2 用于指定生成的代理中有哪些方法 也就是实现了哪些接口
        // param3 用于指定生成的代理对象要干什么 也就是扩展目标对象的方法
        Star starProxy = (Star) Proxy.newProxyInstance(ProxyUtil.class.getClassLoader(),
                new Class[]{Star.class}, new InvocationHandler() {
                    @Override // param1 当前代理对象 param2 代理调用的方法 param3 方法中的参数
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if (method.getName().equals("sing")) {
                            System.out.println("prepare to take microphone...");
                        } else if (method.getName().equals("dance")) {
                            System.out.println("prepare to collect money...");
                        }
                        // 扩展结束后再调用目标对象的方法
                        return method.invoke(bigStar, args);
                    }
                });
        return starProxy;
    }
}
