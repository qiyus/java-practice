package point.thread;

import java.util.Date;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by Vigor on 2017/3/15.
 * 线程同步工具（一）控制并发访问资源
 * 学习怎样使用Java语言提供的Semaphore机制。Semaphore是一个控制访问多个共享资源的计数器。
 */
public class LimitPrinter {
    private final Semaphore semaphore;

    public LimitPrinter() {

        // 默认非公平
        semaphore = new Semaphore(1, true);
    }

    /**
     * 顺序打印
     * @param date 打印任务
     */
    public void printJob(Date date) {
        try {
            semaphore.acquire();
            String threadName = Thread.currentThread().getName();
            System.out.printf("%s: print job [%s]\n", threadName, date);
            TimeUnit.SECONDS.sleep((long) (Math.random() * 10));
            System.out.printf("%s: finish\n", threadName);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }

    public static void main(String[] args) {
        LimitPrinter printer = new LimitPrinter();

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> printer.printJob(new Date()));
            thread.start();
        }
    }
}
