package com.microsoft.proxyPattern.dynamicProxy;

import org.junit.Test;

/**
 * 动态代理:
 * 目标对象和代理对象在运行时才确定 无需手写代理类
 * 利用反射的原理 实现接口和目标类中的方法
 */
public class Run {
    @Test
    public void Test1() {
        BigStar bigStar = new BigStar("jack");
        Star starProxy = ProxyUtil.createStar(bigStar);
        String re = starProxy.sing("see you again");
        System.out.println(re);
        starProxy.dance();

        System.out.println(Star.class);
        System.out.println(bigStar.getClass());
    }
}
