package point.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Phaser;

/**
 * Created by Vigor on 2017/3/17.
 * 线程同步工具（五）运行阶段性并发任务
 * Phaser类提供的机制是在每个步骤的结尾同步线程，所以除非全部线程完成第一个步骤，否则线程不能开始进行第二步。
 * Phaser 类的一个显著特点是你不需要控制任何与phaser相关的方法的异常。
 * 在Phaser内有2个重要状态，分别是phase和party。
 * phase就是阶段，初值为0，当所有的线程执行完本轮任务，同时开始下一轮任务时，意味着当前阶段已结束，进入到下一阶段，phase的值自动加1。
 * party就是线程，party=4就意味着Phaser对象当前管理着4个线程。
 */
public class MatrixPhaseSearcher {
    private Phaser phaser;
    private MatrixMock mock;
    private int search;
    private volatile int sum = 0;

    public MatrixPhaseSearcher(MatrixMock mock, int search) {
        this.mock = mock;
        this.search = search;
        this.phaser = new Phaser(mock.getSize());
    }

    public void search() {
        int size = mock.getSize();
        for (int i = 0; i < size; i++) {
            Thread thread = new Thread(new MatrixPhaseSearcher.Searcher(mock.getRow(i), search));
            thread.start();
        }
    }

    class Searcher implements Runnable {
        private int[] rows;
        private int search;

        public Searcher(int[] rows, int search) {
            this.rows = rows;
            this.search = search;
        }

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            System.out.printf("%s: wait start.\n", name);
            phaser.arriveAndAwaitAdvance();
            int counter = 0;
            for (int j = 0; j < rows.length; j++) {
                if (rows[j] == search) {
                    counter++;
                }
            }
            synchronized (MatrixSearcher.class) {
                sum += counter;
            }

            System.out.printf("%s searched: %d\n", name, counter);
            if (counter < 100) {
                // arriveAndDeregister()通知phaser线程结束了actual phase，但是它将不会继续参见后面的phases,所以请phaser不要再等待它了。
                phaser.arriveAndDeregister();
                System.out.printf("%s parties: %d\n", name, phaser.getArrivedParties());
            } else {
                // rriveAndAwaitAdvance() 方法,像之前提到的，Phaser知道我们要同步的线程的数量。
                // 当某个线程调用此方法，Phaser减少终结actual phase的线程数，并让这个线程进入休眠 直到全部其余线程结束phase。
                System.out.printf("%s parties: %d\n", name, phaser.getArrivedParties());
                phaser.arriveAndAwaitAdvance();
            }

            // Termination: 默认状态，当Phaser里全部的参与者都取消注册，它进入这个状态，所以这时 Phaser 有0个参与者。
            if (phaser.isTerminated()) {
                System.out.printf("%s sum: %d\n", name, sum);
            }

            /**
             * Phaser还有一个重要的方法经常需要被重载，那就是boolean onAdvance(int phase, int registeredParties)方法。
             * 此方法有2个作用：
             * 1、当每一个阶段执行完毕，此方法会被自动调用，因此，重载此方法写入的代码会在每个阶段执行完毕时执行，相当于CyclicBarrier的barrierAction。
             * 2、当此方法返回true时，意味着Phaser被终止，因此可以巧妙的设置此方法的返回值来终止所有线程。
             *    例如：若此方法返回值为 phase>=3，其含义为当整个线程执行了4个阶段后，程序终止。
             */
        }
    }


    public static void main(String[] args) {
        MatrixMock mock = new MatrixMock(5, 1000, 3);
        MatrixPhaseSearcher searcher = new MatrixPhaseSearcher(mock, 3);
        searcher.search();
    }
}
