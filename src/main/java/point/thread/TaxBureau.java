package point.thread;

/**
 * Created by Vigor on 2017/3/13.
 * 基本线程同步（二）同步方法
 */
public class TaxBureau implements Runnable {
    private final Account account;

    public TaxBureau(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        for (int i = 0; i < 12; i++) {
            account.draw(100);
        }
    }

    public static void main(String[] args) {
        Account account = new Account();
        account.setBalance(0);

        Company company = new Company(account);
        TaxBureau taxBureau = new TaxBureau(account);

        Thread companyThread = new Thread(company);
        Thread taxBureauThread = new Thread(taxBureau);
        companyThread.start();
        taxBureauThread.start();

        try {
            companyThread.join();
            System.out.println("save finish:" + account.getBalance());
            taxBureauThread.join();
            System.out.println("draw finish:" + account.getBalance());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
