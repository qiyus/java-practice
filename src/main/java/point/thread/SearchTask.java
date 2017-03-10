package point.thread;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by Vigor on 2017/3/10.
 * 线程管理（十）线程组
 * ava并发 API里有个有趣的方法是把线程分组。这个方法允许我们按线程组作为一个单位来处理。
 */
public class SearchTask implements Runnable {
    private SearchFinishedListener listener;
    private final List<String> content;
    private final String key;

    public SearchTask(List<String> content, String key) {
        this.content = content;
        this.key = key;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.printf("%s: Start\n", threadName);
        for (String s : content) {
            try {
                if (s.equals(key)) {
                    listener.onFinish(threadName);
                }

                Random random = new Random((new Date()).getTime());
                int value = (int) (random.nextDouble() * 100);
                TimeUnit.MILLISECONDS.sleep(value);

                if (Thread.interrupted()) {
                    System.out.printf("%s: The search has been interrupted. \n", threadName);
                    return;
                }
            } catch (InterruptedException e) {
                System.out.printf("%s: The search has been interrupted.\n", threadName);
                return;
            }
        }
        System.out.printf("%s: End\n", threadName);
    }

    /**
     * 设置回调。
     * @param listener 回调对象。
     */
    public void setListener(SearchFinishedListener listener) {
        this.listener = listener;
    }

    public static void main(String[] args) {
        // 构造数据
        List<String> content = new ArrayList<>(10000);
        for (int i = 0; i < 10000; i++) {
            content.add(String.valueOf(i));
        }

        final String key = "8128";

        // 创建线程组
        ThreadGroup threadGroup = new ThreadGroup("Searcher");

        // 分10组启动线程进行搜索
        for (int i = 0; i < 10; i++) {
            int fromIndex = i * 1000;
            SearchTask task = new SearchTask(content.subList(fromIndex, fromIndex + 999), key);
            task.setListener(threadName -> {
                // 搜索成功，停止其它搜索线程。
                System.out.printf("%s: search success.\n", threadName);
                threadGroup.interrupt();
            });
            Thread thread = new Thread(threadGroup, task);

            thread.start();
        }

        // 等待全部线程完成
        waitFinish(threadGroup);
    }

    /**
     * 等待全部线程结束。
     * @param group 线程组。
     */
    private static void waitFinish(ThreadGroup group) {
        while (group.activeCount() <= 10) {
            try {
                // 输出线程状态
                int count = group.activeCount();
                Thread[] threads = new Thread[count];
                group.enumerate(threads);
                for (int i = 0; i < count; i++) {
                    System.out.printf("Thread %s: %s\n", threads[i].getName(), threads[i].getState());
                }

                // 睡眠
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
