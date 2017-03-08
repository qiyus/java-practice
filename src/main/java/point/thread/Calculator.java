package point.thread;

/**
 * Created by Vigor on 2017/3/3.
 * 并发编程练习
 * 线程管理（一）线程的创建和运行
 * 线程管理（二）获取和设置线程信息。
 *
 * 笔记：
 * id：每个线程的标识。
 * name：线程的名字。
 * priority：优先级在1-10之间。
 * status：线程的状态，new|runnable|blocked|waiting|time waiting|terminated
 */
public class Calculator implements Runnable {
    private final int number;
    private static final int MAX = 9;

    private Calculator(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        for (int i = 1; i <= number; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 输出计算结果。
            System.out.printf("%s(%d): %d * %d = %d\n", Thread.currentThread().getName(),
                    Thread.currentThread().getPriority(), i, number, i * number);
        }
    }

    public static void main(String[] args) {
        Thread threads[] = new Thread[MAX];
        Thread.State state = Thread.State.NEW;

        // 初始化线程。
        for (int i = 0; i < MAX; i++) {
            threads[i] = new Thread(new Calculator(i + 1));
            if (i % 2 == 0) {
                threads[i].setPriority(Thread.MAX_PRIORITY);
            } else {
                threads[i].setPriority(Thread.MIN_PRIORITY);
            }
        }

        // 启动线程。
        for (int i = 0; i < MAX; i++) {
            threads[i].start();
        }

        // 查询线程9的状态并打印至线程结束。
        while (true) {
            Thread.State temp = threads[MAX-1].getState();
            if (!temp.equals(state)) {
                System.out.printf("%s: %s ==> %s\n", threads[MAX-1].getName(), state, temp);
                state = temp;
            }

            if (temp.equals(Thread.State.TERMINATED)) {
                break;
            }
        }
    }
}
