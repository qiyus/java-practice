package point.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Vigor on 2017/3/14.
 * 基本线程同步（六）使用读/写锁同步数据访问
 * 基本线程同步（七）修改Lock的公平性
 * 线程进入读锁的前提条件：
 * 没有其他线程的写锁，
 * 没有写请求或者有写请求，但调用线程和持有锁的线程是同一个
 * 线程进入写锁的前提条件：
 * 没有其他线程的读锁
 * 没有其他线程的写锁
 */
public class PricesInfo {
    private double price1;
    private double price2;
    private ReadWriteLock lock;

    /**
     * 在ReentrantLock类和 ReentrantReadWriteLock类的构造器中，
     * 允许一个名为fair的boolean类型参数，它允许你来控制这些类的行为。默认值为 false，这将启用非公平模式。
     * true值将开启公平 模式。在这个模式中，当有多个线程正在等待一把锁（ReentrantLock或者ReentrantReadWriteLock），
     * 这个锁必须选择它们中间的一个来获得进入临界区，它将选择等待时间最长的线程。
     */
    public PricesInfo() {
        price1 = 1.0;
        price2 = 2.0;
        lock = new ReentrantReadWriteLock(true);
    }

    public void printPrices() {
        lock.readLock().lock();
        System.out.printf("price1: %f; price2: %f\n", price1, price2);
        sleep();
        lock.readLock().unlock();
    }

    public void addOne() {
        lock.writeLock().lock();
        price1 += 1;
        price2 += 1;
        System.out.printf("price1, price2: add one\n");
        sleep();
        lock.writeLock().unlock();
    }

    private void sleep() {
        long duration = (long) (Math.random() * 10);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
