package point.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

/**
 * Created by Vigor on 2017/3/20.
 * 线程同步工具（七）在并发任务间交换数据
 * Exchanger 类允许在2个线程间定义同步点，当2个线程到达这个点，他们相互交换数据类型，
 * 使用第一个线程的数据类型变成第二个的，然后第二个线程的数据类型变成第一个的。
 */
public class YoungerBrother implements Runnable {
    private List<String> buffer;
    private Exchanger<List<String>> exchanger;

    public YoungerBrother(Exchanger<List<String>> exchanger) {
        buffer = new ArrayList<>();
        this.exchanger = exchanger;
        for (int i = 0; i < 10; i++) {
            buffer.add("Younger brother's ice cream " + i);
        }
    }

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep((long) (Math.random() * 10));
            List<String> brother = exchanger.exchange(buffer);
            System.out.println("Younger brother:" + brother.get(brother.size() - 1));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
