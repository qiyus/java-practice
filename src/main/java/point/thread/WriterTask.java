package point.thread;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.TimeUnit;

/**
 * Created by Vigor on 2017/3/9.
 * 线程管理（七）守护线程的创建和运行
 */
public class WriterTask implements Runnable{
    Deque<String> deque;

    public WriterTask(Deque<String> deque) {
        this.deque = deque;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            String item = String.format("%s: %d", Thread.currentThread().getName(), deque.size());
            System.out.println(item);
            deque.addFirst(item);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Deque<String> deque = new ArrayDeque<>();
        WriterTask writerTask = new WriterTask(deque);
        for (int i = 0; i < 2; i++) {
            Thread thread = new Thread(writerTask);
            thread.start();
        }

        CleanerTask cleanerTask = new CleanerTask(deque);
        cleanerTask.start();

        try {
            TimeUnit.SECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
