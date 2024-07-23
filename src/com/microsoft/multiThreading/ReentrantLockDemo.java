package com.microsoft.multiThreading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {
    public static void main(String[] args) {
        LockDemo demo = new LockDemo();

        new Thread(demo, "fuck").start();
    }
}

// synchronized 不需要用户去手动释放锁，代码执行完后系统会自动让线程释放对锁的占用 它依赖于JVM来实现锁的机制。 锁的获取和释放由JVM自动完成，无需手动管理。 默认是非公平的
// reentrantLock则需要用户去手动释放锁，如果没有手动释放锁，就可能导致死锁现象 支持重入特性，即同一线程可以多次获得同一把锁而不会被阻塞。
// 在高并发环境下，ReentrantLock的性能通常比synchronized更好。
class LockDemo implements Runnable {
    Lock reentrantLock = new ReentrantLock();

    @Override
    public void run() {
        int n = 10;
        while (true) {
            reentrantLock.lock();
            // reentrantLock if判断需要在try中
            try {
                if (n > 0) {
                    Thread.sleep(500);
                    System.out.println(Thread.currentThread().getName() + " " + n--);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // 释放锁
                reentrantLock.unlock();
            }
        }
    }
}
