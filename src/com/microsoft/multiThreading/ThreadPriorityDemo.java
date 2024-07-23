package com.microsoft.multiThreading;

public class ThreadPriorityDemo {
    public static void main(String[] args) throws InterruptedException {
        // 每一个线程的优先使用权都是系统随机分配的，人人平等，谁先分配到谁先用
        // Thread类priority从1到10 默认为5 java可以抢占cpu
        Thread maxPriorityThread = new Thread(new MaxPriorityThread(), "maxPriorityThread");
        Thread minPriorityThread = new Thread(new MinPriorityThread(), "minPriorityThread");
        // 设置优先级 但是程序执行太快了没有反应过来，还没调度程序就结束了
        maxPriorityThread.setPriority(Thread.MIN_PRIORITY);

        maxPriorityThread.start();
        minPriorityThread.start();

        for (int i = 1; i < 8; i++) {
            System.out.println(Thread.currentThread().getName());
            if (i == 3) {
                // join方法会使主线程会进入等待状态，一直到调用join()方法的线程执行结束为止。
                // join底层使用wait(0)方法使得主线程永久等待 自动调用notify() 使其恢复
                maxPriorityThread.join();
            }
        }
    }
}

class MaxPriorityThread implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            if (i == 1) {
                System.out.println("让步。。。。。。");
                // 线程进行让步 让其他线程执行
                Thread.yield();
            }
            try {
                // 线程休眠等待1000毫秒再执行
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        }
    }
}

class MinPriorityThread implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            try {
                // 线程休眠等待1000毫秒再执行
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        }
    }
}