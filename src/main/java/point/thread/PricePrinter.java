package point.thread;

/**
 * Created by Vigor on 2017/3/14.
 * 基本线程同步（六）使用读/写锁同步数据访问
 */
public class PricePrinter implements Runnable {
    PricesInfo pricesInfo;

    public PricePrinter(PricesInfo pricesInfo) {
        this.pricesInfo = pricesInfo;
    }

    @Override
    public void run() {
        pricesInfo.printPrices();
    }

    public static void main(String[] args) {
        PricesInfo info = new PricesInfo();
        PricePrinter printer = new PricePrinter(info);
        PricesInflation inflation = new PricesInflation(info);

        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(inflation);
            thread.start();
        }

        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(printer);
            thread.start();
        }
    }
}
