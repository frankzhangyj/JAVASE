package com.microsoft.proxyPattern.staticProxy;

/**
 * 静态代理:
 * 目标对象和代理对象之间的关系在编译时就已经确定
 * 代理类和目标类实现相同的接口 代理类中需要有目标类的引用
 */
public class ProxyPattern {
    public static void main(String[] args) {
        Tiger tiger = new Tiger();
        ThreadProxy threadProxy = new ThreadProxy(tiger);
        threadProxy.start();
    }
}

class Animal {}

// 通过实现Runnable接口可以实现继承别的类
class Tiger extends Animal implements Runnable {

    @Override
    public void run() {
        System.out.println("老虎...");
    }
}

//线程代理类，模拟了一个极简的Thread类 实际不会创建一个新线程
class ThreadProxy implements Runnable { //可以把 Proxy 类当做 Thread

    private Runnable target = null; // 属性类型 是 Runnable

    @Override
    public void run() {
        if (target != null) {
            target.run();//动态绑定(运行类型是Tiger)
        }
    }

    public ThreadProxy(Tiger target) {
        this.target = target;
    }

    public void start() {
        start0();//这个方法是真正实现多线程的方法
    }

    public void start0() {
        run();
    }
}
