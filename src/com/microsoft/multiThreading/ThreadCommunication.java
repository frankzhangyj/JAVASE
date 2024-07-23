package com.microsoft.multiThreading;
// 等待队列中的线程都处于等待状态 需要被唤醒才能加入同步队列
// 同步队列中的线程都处于就绪状态 都想争夺对象锁
public class ThreadCommunication {
    public static void main(String[] args) {
        // 生产者线程和消费者线程通过一个共享对象 Condom 进行通信和协调
        Condom condom = new Condom();
        // 消费者线程
        Thread customer = new Thread(new Customer(condom), "customer");
        // 生产者线程
        Thread producer = new Thread(new Producer(condom), "producer");
        customer.start();
        producer.start();
    }
}

class Condom {
    public boolean isStatus = false;
}

class Customer implements Runnable {
    private Condom condom;
    public Customer(Condom condom) {
        this.condom = condom;
    }
    @Override
    public void run() {

        while (true) {
            // 调用obj的wait(), notify()方法前，必须获得obj锁，也就是必须写在synchronized(obj) 代码段内。
            // 先对condom对象上锁 其他线程无法使用该对象
            synchronized (condom) {
                // 判断是否为flase 确保消费者线程不会一直执行
                // 如果为false 表示没有产品可以消费，
                if (condom.isStatus == false) {
                    try {
                        // 消费者线程调用 condom.wait() 使自己进入等待状态，并释放 condom 对象的锁，等待被唤醒。
                        condom.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                condom.isStatus = false;
                System.out.println(Thread.currentThread().getName() + "我们卖完了 可以生产了 唤醒生产者");
                condom.notify();
            }
        }
    }
}

class Producer implements Runnable {
    private Condom condom;
    public Producer(Condom condom) {
        this.condom = condom;
    }
    @Override
    public void run() {
        while (true) {
            synchronized (condom) {
                // 判断是否为true 确保生产者线程不会一直执行
                if (condom.isStatus == true) {
                    try {
                        // 生产者线程调用 condom.wait() 使自己进入等待状态，并释放 condom 对象的锁，等待被唤醒。
                        condom.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 当为false 生产者生产产品 唤醒等待的消费者线程。
                condom.isStatus = true;
                System.out.println(Thread.currentThread().getName() + "我们生产完了 你可以买了 唤醒消费者");
                condom.notify();
            }
        }
    }
}
