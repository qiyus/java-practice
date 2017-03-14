package point.thread;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Vigor on 2017/3/14.
 * 基本线程同步（四）在同步代码中使用条件
 */
public class EventStorage {
    private static final int MAX_SIZE = 10;
    private List<Date> storage = new LinkedList<>();

    /**
     * 一个线程可以在synchronized代码块中调用wait()方法。如果在synchronized代码块外部调用wait()方法，
     * JVM会抛出IllegalMonitorStateException异常。当线程调用wait()方法，JVM让这个线程睡眠，
     * 并且释放控制 synchronized代码块的对象，这样，虽然它正在执行但允许其他线程执行由该对象保护的其他synchronized代码块。
     * 为了唤醒线程，你必须在由相同对象保护的synchronized代码块中调用notify()或notifyAll()方法。
     */
    public void set() {
        synchronized (this) {
            while (storage.size() == MAX_SIZE) {
                try {
                    wait();
                    System.out.println("wait():" + storage.size());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            storage.add(new Date());
            System.out.printf("set size: %d\n", storage.size());
            notify();
            System.out.println("notify()");
        }
    }

    public void get() {
        synchronized (this) {
            while (storage.size() == 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            Date date = storage.remove(0);
            System.out.println("get: " + date);
            notify();
        }
    }
}
