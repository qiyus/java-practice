package point.thread;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Vigor on 2017/3/27.
 * 线程执行者（八）执行者周期性地运行一个任务
 */
public class TaskSchedule implements Runnable {
    @Override
    public void run() {
        try {
            System.out.printf("current date %s \n", new Date());
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.scheduleAtFixedRate(new TaskSchedule(), 1, 3, TimeUnit.SECONDS);
        System.out.printf("Main: Starting at: %s\n", new Date());
    }
}
