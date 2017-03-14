package point.thread;

import java.util.Date;

/**
 * Created by Vigor on 2017/3/14.
 * 基本线程同步（四）在同步代码中使用条件
 */
public class Consumer implements Runnable{
    private EventStorage storage;

    public Consumer(EventStorage storage) {
        this.storage = storage;
    }


    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            storage.get();
        }
    }

    public static void main(String[] args) {
        EventStorage storage = new EventStorage();
        Thread producer = new Thread(new Producer(storage));
        Thread consumer = new Thread(new Consumer(storage));
        producer.start();
        consumer.start();
    }
}
