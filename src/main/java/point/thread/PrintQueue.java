package point.thread;

import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Vigor on 2017/3/14.
 * 基本线程同步（五）使用Lock同步代码块
 */
public class PrintQueue {
    private Queue<Date> queue = new LinkedList();
    private final Lock lock = new ReentrantLock();

    public boolean addJob(Date date) {
        return queue.offer(date);
    }

    public void printJob() {
        lock.lock();
        Long duration=(long)(Math.random()*10000);
        Date job = queue.poll();
        if (job == null) {
            System.out.println("print finish!");
        } else {
            System.out.printf("job %d: %s\n", queue.size(), job);
        }

        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.unlock();
    }
}
