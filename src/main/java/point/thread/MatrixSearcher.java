package point.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by Vigor on 2017/3/16.
 * 线程同步工具（四）在同一个点同步任务
 * CyclicBarrier 类有一个整数初始值，此值表示将在同一点同步的线程数量。
 * 当其中一个线程到达确定点，它会调用await() 方法来等待其他线程。当线程调用这个方法，CyclicBarrier阻塞线程进入休眠直到其他线程到达。
 * 当最后一个线程调用CyclicBarrier 类的await() 方法，它唤醒所有等待的线程并继续执行它们的任务。
 */
public class MatrixSearcher {
    private CyclicBarrier barrier;
    private MatrixMock mock;
    private int search;
    private volatile int sum = 0;

    public MatrixSearcher(MatrixMock mock, int search) {
        this.mock = mock;
        this.search = search;
        this.barrier = new CyclicBarrier(mock.getSize(), new BarrierPrinter());
    }

    public void search() {
        int size = mock.getSize();
        for (int i = 0; i < size; i++) {
            Thread thread = new Thread(new Searcher(mock.getRow(i), search));
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
            int counter = 0;
            try {
                for (int j = 0; j < rows.length; j++) {
                    if (rows[j] == search) {
                        counter++;
                    }
                }
                synchronized (MatrixSearcher.class) {
                    sum += counter;
                }
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    class BarrierPrinter implements Runnable {

        @Override
        public void run() {
            System.out.printf("searched counter: %d\n", sum);
            System.out.printf("Parties counter: %d\n", barrier.getParties());
            System.out.printf("waiting counter: %d\n", barrier.getNumberWaiting());
        }
    }

    public static void main(String[] args) {
        MatrixMock mock = new MatrixMock(5, 1000, 3);
        MatrixSearcher searcher = new MatrixSearcher(mock, 3);
        searcher.search();
    }
}
