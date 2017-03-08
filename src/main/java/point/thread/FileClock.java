package point.thread;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Vigor on 2017/3/8.
 * 线程管理（五）线程的睡眠和恢复
 */
public class FileClock implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {

            System.out.printf("%d: %s\n", i, new Date());
            try {
                // 在调用sleep() 方法后，当时间结束时，当JVM安排他们CPU时间，线程会继续按指令执行，
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.printf("%d: The FileClock has been interrupted\n", i);
            }
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new FileClock());
        thread.start();

        try {
            TimeUnit.SECONDS.sleep(7);
            thread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
