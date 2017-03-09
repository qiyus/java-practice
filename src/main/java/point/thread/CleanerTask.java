package point.thread;

import java.util.Deque;
import java.util.concurrent.TimeUnit;

/**
 * Created by Vigor on 2017/3/9.
 * 线程管理（七）守护线程的创建和运行
 */
public class CleanerTask extends Thread {
    private Deque<String> deque;

    public CleanerTask(Deque<String> deque) {
        this.deque = deque;
        setDaemon(true);
    }

    @Override
    public void run() {
        while (true) {
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            if (deque.size() > 5) {
                System.out.println(Thread.currentThread().getName());
                deque.removeLast();
            }
        }
    }
}
