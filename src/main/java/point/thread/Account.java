package point.thread;

import java.util.concurrent.TimeUnit;

/**
 * Created by Vigor on 2017/3/13.
 * 基本线程同步（二）同步方法
 */
public class Account {
    private double balance;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void save(double increment) {
        synchronized (this) {
            double temp = balance;
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            temp += increment;
            balance = temp;
            System.out.printf("save balance: %f\n", balance);
        }
    }

    public void draw(double decrement) {
        synchronized (this) {
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            balance -= decrement;
            System.out.printf("draw balance: %f\n", balance);
        }
    }
}
