package point.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

/**
 * Created by Vigor on 2017/3/20.
 * 线程同步工具（七）在并发任务间交换数据
 */
public class Brother implements Runnable {
    private List<String> buffer;
    private Exchanger<List<String>> exchanger;

    public Brother(Exchanger<List<String>> exchanger) {
        this.exchanger = exchanger;
        buffer = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            buffer.add("Brother's ice cream " + i);
        }
    }

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(5);
            List<String> younger = exchanger.exchange(buffer);
            System.out.println("Brother:" + younger.get(younger.size() - 1));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Exchanger<List<String>> exchanger = new Exchanger<>();
        Thread t1 = new Thread(new Brother(exchanger));
        Thread t2 = new Thread(new YoungerBrother(exchanger));
        t1.start();
        t2.start();
    }
}
