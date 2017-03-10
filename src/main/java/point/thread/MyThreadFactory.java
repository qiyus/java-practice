package point.thread;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadFactory;

/**
 * Created by Vigor on 2017/3/10.
 * 线程管理（十二）用线程工厂创建线程
 */
public class MyThreadFactory implements ThreadFactory {
    private int counter;
    private final String name;
    private List<String> info = new ArrayList<>();

    public MyThreadFactory(String name) {
        this.name = name;
    }

    public Iterator<String> getCreateInfo() {
        return info.iterator();
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, name + "_" + counter);
        info.add(String.format("created thread %d with name %s on %s\n", t.getId(), t.getName(), new Date()));
        counter++;
        return t;
    }

    public static void main(String[] args) {
        MyThreadFactory factory = new MyThreadFactory("test");
        FileClock clock = new FileClock();
        for (int i = 0; i < 10; i++) {
            factory.newThread(clock).start();
        }

        Iterator<String> iterator = factory.getCreateInfo();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
