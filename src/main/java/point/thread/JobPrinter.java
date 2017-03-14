package point.thread;

import java.util.Date;

/**
 * Created by Vigor on 2017/3/14.
 * 基本线程同步（五）使用Lock同步代码块
 */
public class JobPrinter implements Runnable{
    private PrintQueue queue;

    public JobPrinter(PrintQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        queue.printJob();
    }

    public static void main(String[] args) {
        PrintQueue queue = new PrintQueue();
        for (int i = 0; i < 10; i++) {
            queue.addJob(new Date());
        }

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new JobPrinter(queue));
            thread.start();
        }
    }
}
