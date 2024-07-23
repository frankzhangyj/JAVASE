package com.microsoft.multiThreading;

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

//线程代理类，模拟了一个极简的Thread类
class ThreadProxy implements Runnable { //可以把 Proxy 类当做 Thread

    private Runnable target = null; // 属性类型 是 Runnable

    @Override
    public void run() {
        if (target != null) {
            target.run();//动态绑定(运行类型是Tiger)
        }
    }

    public ThreadProxy(Runnable target) {
        this.target = target;
    }

    public void start() {
        start0();//这个方法是真正实现多线程的方法
    }

    public void start0() {
        run();
    }
}
