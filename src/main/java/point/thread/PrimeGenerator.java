package point.thread;

/**
 * Created by Vigor on 2017/3/3.
 * 并发编程练习
 * 线程管理（三）线程的中断
 */
public class PrimeGenerator extends Thread{

    @Override
    public void run() {
        long number = 1L;
        while (true) {
            if (isPrime(number)) {
                System.out.println(number);
            }

            if (isInterrupted()) {
                System.out.printf("The Prime Generator has been Interrupted");
                return;
            }

            number++;
        }
    }

    private boolean isPrime(long number) {
        if (number <= 2) {
            return true;
        }

        for (long i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Thread thread = new PrimeGenerator();
        thread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt();
    }
}
