package point.thread;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Vigor on 2017/4/6.
 * 定制并发类（三）实现一个基于优先级的Executor类
 */
public class PriorityTask implements Runnable, Comparable<PriorityTask> {
    private int priority;
    private String name;

    public PriorityTask(int priority, String name) {
        this.priority = priority;
        this.name = name;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public void run() {
        System.out.printf("PriorityTask: %s Priority : % d\n", name, priority);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int compareTo(PriorityTask o) {
        if (this.getPriority() < o.getPriority()) {
            return 1;
        } else if (this.getPriority() > o.getPriority()) {
            return -1;
        }
        return 0;
    }

    public static void main(String[] args) {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2, 2, 1, TimeUnit.SECONDS, new PriorityBlockingQueue<Runnable>());
        for (int i = 0; i < 8; i++) {
            PriorityTask task = new PriorityTask(i, "task" + i);
            poolExecutor.execute(task);
        }
        poolExecutor.shutdown();
        try {
            poolExecutor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Main: End of the program.\n");
    }
}
