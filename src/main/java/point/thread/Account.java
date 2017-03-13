package point.thread;

import java.util.concurrent.TimeUnit;

/**
 * Created by Vigor on 2017/3/13.
 * 基本线程同步（二）同步方法
 * 你使用synchronized关键字来保护代码块时，你必须通过一个对象的引用作为参数。
 * 通常，你将会使用this关键字来引用执行该方法的对象。
 *
 * 基本线程同步（三）在同步的类里安排独立属性
 * 你也可以使用其他对象引用，这些对象被创建只有这个目的。比如，你在一个类中有被多个线程共享的两个独立属性。
 * 你必须同步访问每个变量，如果有一个线程访问一个属性和另一个线程在同一时刻访问另一个属性，这是没有问题的。
 * 比如电影院2个售票窗口同时卖多部电影的票。
 *
 * 1、当两个并发线程访问同一个对象object中的这个synchronized(this)同步代码块时，一个时间内只能有一个线程得到执行。另
 * 一个线程必须等待当前线程执行完这个代码块以后才能执行该代码块。
 * 2、然而，当一个线程访问object的一个synchronized(this)同步代码块时，另一个线程仍然可以访问该object中的非synchronized(this)同步代码块。
 * 3、当一个线程访问object的一个synchronized(this)同步代码块时，其他线程对object中所有其它synchronized(this)同步代码块的访问将被阻塞。
 * 4、sleep() 使当前线程（即调用该方法的线程）暂停执行一段时间，让其他线程有机会继续执行，但它并不释放对象锁。
 * 5、join()方法使调用该方法的线程在此之前执行完毕，也就是等待该方法的线程执行完毕后再往下继续执行。
 * 6、yield() 该方法与sleep()类似，只是不能由用户指定暂停多长时间，并且yield（）方法只能让同优先级的线程有执行的机会。
 * 7、wait()方法使当前线程暂停执行并释放对象锁标示，让其他线程可以进入synchronized数据块，当前线程被放入对象等待池中。
 *    当调用notify()方法后，将从对象的等待池中移走一个任意的线程并放到锁标志等待池中，只有锁标志等待池中线程能够获取锁标志；
 *    如果锁标志等待池中没有线程，则notify()不起作用。
 * 8、notifyAll()则从对象等待池中移走所有等待那个对象的线程并放到锁标志等待池中。
 */
public class Account {
    private double balance;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void save(double increment) {
        synchronized (this) {
            double temp = balance;
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            temp += increment;
            balance = temp;
            System.out.printf("save balance: %f\n", balance);
        }
    }

    public void draw(double decrement) {
        synchronized (this) {
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            balance -= decrement;
            System.out.printf("draw balance: %f\n", balance);
        }
    }
}
