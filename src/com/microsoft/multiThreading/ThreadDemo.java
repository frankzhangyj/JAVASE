package com.microsoft.multiThreading;

import java.util.TreeMap;

public class ThreadDemo {
    public static void main(String[] args) {
        // 直接用类继承Thread类来创建线程执行
        Demo demo = new Demo();
        // 如果直接调用run方法 那么就会一直死循环
//        demo.run();
        // 如果使用start方法 可以再创建一个线程 自动执行run方法 然后和当前线程交替执行
        demo.start();

        // 类不能多继承 继承了Thread就不能再继承别的了 所以可以实现Runnable接口来实现线程执行
        // 必须再创建一个Thread变量来执行这个类 (代理设计模式)
        RunnableDemo runnableDemo = new RunnableDemo();
        // 创建线程时可以设置线程名 默认是Thread + 号
        Thread thread = new Thread(runnableDemo, "-fuck");
        thread.start();

        // 匿名内部类创建多线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(Thread.currentThread().getName());
                }
            }
        }).start();

        while (true) {
            System.out.println("mainThread");
        }
    }
}

class Demo extends Thread {
    @Override
    public void run() {
        while (true) {
            // 在原来线程类中自动获取我们在主线程中设置的名字 currentThread()当前线程 getName()得到线程名
            System.out.println("DemoThread" + Thread.currentThread().getName());
        }
    }
}

// 接口Runnable
class RunnableDemo implements Runnable {

    @Override
    public void run() {
        while (true) {
            System.out.println("RunnableThread" + Thread.currentThread().getName()  );
        }
    }
}
