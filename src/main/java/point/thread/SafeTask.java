package point.thread;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Vigor on 2017/3/10.
 * 线程管理（九）使用本地线程变量
 */
public class SafeTask implements Runnable {
    private Date startDate = new Date();
    private ThreadLocal<Date> finishedDate = ThreadLocal.withInitial(() -> new Date());

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep((int) Math.rint(Math.random() * 10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Thread: %s : Starting at %s. \n", Thread.currentThread().getName(), startDate);
        System.out.printf("Thread: %s : Finished at %s. \n", Thread.currentThread().getName(), finishedDate.get());
    }

    public static void main(String[] args) {
        SafeTask task = new SafeTask();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(task);
            thread.start();
        }
    }
}
