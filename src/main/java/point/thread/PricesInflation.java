package point.thread;

/**
 * Created by Vigor on 2017/3/14.
 * 基本线程同步（六）使用读/写锁同步数据访问
 */
public class PricesInflation implements Runnable{
    PricesInfo pricesInfo;

    public PricesInflation(PricesInfo pricesInfo) {
        this.pricesInfo = pricesInfo;
    }

    @Override
    public void run() {
        pricesInfo.addOne();
    }
}
