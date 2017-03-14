package point.thread;

/**
 * Created by Vigor on 2017/3/14.
 * 基本线程同步（四）在同步代码中使用条件
 */
public class Producer implements Runnable{
    private EventStorage storage;

    public Producer(EventStorage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            storage.set();
        }
    }
}
