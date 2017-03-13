package point.thread;

/**
 * Created by Vigor on 2017/3/13.
 * 基本线程同步（二）同步方法
 */
public class Company implements Runnable{
    private final Account account;

    public Company(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        for (int i = 0; i < 12; i++) {
            account.save(100);
        }
    }
}
