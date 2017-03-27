package point.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Vigor on 2017/3/27.
 * 线程执行者（六）运行多个任务并处理所有结果
 */
public class TaskInvoker {
    public static void main(String[] args) {
        List<TaskExecutor> tasks = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            tasks.add(new TaskExecutor(i));
        }

        ExecutorService executors = Executors.newCachedThreadPool();
        List<Future<Integer>> results = null;
        try {
            results = executors.invokeAll(tasks);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executors.shutdown();

        for (Future<Integer> f : results) {
            try {
                System.out.printf("invoke result: %d\n", f.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
