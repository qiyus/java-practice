package point.thread;

import java.util.concurrent.TimeUnit;

/**
 * Created by Vigor on 2017/3/8.
 * 线程管理（六）等待线程的终结
 */
public class DataSourcesLoader implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.printf("%s: %d\n", Thread.currentThread().getName(), i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Thread dataLoader = new Thread(new DataSourcesLoader());
        Thread fileClock = new Thread(new FileClock());

        dataLoader.start();

        try {
            // 我们使用Thread类的join()方法。当前线程调用某个线程的这个方法时，它会暂停当前线程，直到被调用线程执行完成。
            dataLoader.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        fileClock.start();
    }
}
