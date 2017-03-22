package point.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by Vigor on 2017/3/22.
 * 线程执行者（二）创建一个线程执行者
 * 线程执行者（三）创建一个大小固定的线程执行者
 * 线程执行者（四）执行者执行返回结果的任务
 */
public class TaskExecutor implements Callable<Integer> {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            executor.execute(new SafeTask());
            System.out.printf("----------Pool Size: %d\n", executor.getPoolSize());
            System.out.printf("Active Count: %d\n", executor.getActiveCount());
            System.out.printf("Completed Tasks: %d\n", executor.getCompletedTaskCount());
        }

        List<Future<Integer>> resultList = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            Future<Integer> result = executor.submit(new TaskExecutor(i));
            resultList.add(result);
        }

        do {
            try {
                TimeUnit.SECONDS.sleep(10);
                System.out.printf("active count: %d\n", executor.getActiveCount());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (executor.getActiveCount() > 0);

        for (Future<Integer> f: resultList) {
            try {
                System.out.println(f.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();
    }

    private Integer number;

    public TaskExecutor(Integer number) {
        this.number = number;
    }

    @Override
    public Integer call() throws Exception {
        int result = number;
        for (int i = number; i > 1; i--) {
            result *= i - 1;
            TimeUnit.MILLISECONDS.sleep(10);
        }
        return result;
    }
}
