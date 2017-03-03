package point.thread;

/**
 * Created by Vigor on 2017/3/3.
 * 并发编程练习
 * 1,线程的创建和运行。
 * 2,获取和设置线程信息。
 *
 * 笔记：
 * id：每个线程的标识。
 * name：线程的名字。
 * priority：优先级在1-10之间。
 * status：线程的状态，new|runnable|blocked|waiting|time waiting|terminated
 */
public class Calculator implements Runnable {
    private final int number;

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
            System.out.printf("%s(%d): %d * %d = %d\n", Thread.currentThread().getName(),
                    Thread.currentThread().getPriority(), i, number, i * number);
        }
    }

    public static void main(String[] args) {
        Thread threads[] = new Thread[10];
        Thread.State state = Thread.State.NEW;

        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(new Calculator(i + 1));
            if (i % 2 == 0) {
                threads[i].setPriority(Thread.MAX_PRIORITY);
            } else {
                threads[i].setPriority(Thread.MIN_PRIORITY);
            }
        }

        for (int i = 0; i < 10; i++) {
            threads[i].start();
        }

        while (true) {
            Thread.State temp = threads[9].getState();
            if (!temp.equals(state)) {
                System.out.printf("%s: %s ==> %s\n", threads[9].getName(), state, temp);
                state = temp;
            }

            if (threads[9].getState().equals(Thread.State.TERMINATED)) {
                break;
            }
        }
    }
}
