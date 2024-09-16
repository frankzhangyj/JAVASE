package com.microsoft.multiThreading;
// synchronized方法不能被子类继承 需要显示的标记出来
// 在定义接口方法时不能使用synchronized关键字。
// 构造方法不能使用synchronized关键字，但可以使用synchronized代码块来进行同步。

// 无论synchronized关键字加在方法上还是对象上，如果它作用的对象是非静态的，则它取得的锁是对象实例；否则是类实例
// 同步块: 具体对于成员变量锁 sycronized(object)可以锁多个成员变量对象 对于this sycronized(this)只能锁定一个当前对象实例
// 同步方法: 成员方法 sycronized关键字 会锁定当前对象实例(内部对象锁)相当与同步块中sycronized(this) 对于静态方法 sycronized只能锁定Class

// 实现同步是要很大的系统开销作为代价的，甚至可能造成死锁(方法 run() 声明为 synchronized)，所以尽量避免无谓的同步控制。
// synchronized中的wait() 和 notifyall() 方法相当于 reentrantLock中的 条件锁Condition 中await() 和 signalAll()
public class BuySneakerDemo {
    public static void main(String[] args) {
        SneakerShop shop = new SneakerShop();
        // 创建一个后台线程 用来服务前台线程
        DaemonThread daemonThread = new DaemonThread();
        Thread DThread = new Thread(daemonThread);
        // 将其标记为后台线程（守护线程）
        DThread.setDaemon(true);
        DThread.start();
        // 一个人就是一个线程 同一个shop对象 运用synchronized所以线程之间会制约
        new Thread(shop, "frank").start();
        new Thread(shop, "jack").start();
        new Thread(shop, "jane").start();
    }
}

/**
 * 十双鞋 三个人抢
 */
class SneakerShop implements Runnable {
    private int nike = 10;
    // 线程同步，及时更新数据，即创建一个synchronized锁对象，同步数据
    // java中所有对象都可以上锁 object对象创建需要7行操作码
//    Object lock = new Object();
    // 创建一个0长度的byte数组只需要3行操作码
    private byte[] lock = new byte[0];
    @Override
    public synchronized void run() {
        while (true) {
            // 在同一时刻只能有一个线程得到执行，另一个线程受阻塞，必须等待当前线程执行完这个代码块以后才能执行该代码块。
//            synchronized (lock) {
//                if (nike > 0) {
//                    try {
//                        Thread.sleep(500);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    System.out.println(Thread.currentThread().getName() + "抢到了第" + (nike--) + "双鞋");
//                }
//            }
            shoeCatch();
        }
    }
    // synchronized可以创建成一个同步方法，将同步代码块抽离出来
    public synchronized void shoeCatch() {
        if (nike > 0) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "抢到了第" + (nike--) + "双鞋");
        }
    }
}

class DaemonThread implements Runnable {
    @Override
    public void run() {
        System.out.println("守护进程。。。。");
    }
}
