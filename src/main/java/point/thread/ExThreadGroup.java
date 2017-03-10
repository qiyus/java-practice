package point.thread;

/**
 * Created by Vigor on 2017/3/10.
 * 线程管理（十一）处理线程组内的不受控制异常
 */
public class ExThreadGroup extends ThreadGroup{
    public ExThreadGroup(String name) {
        super(name);
    }

    /**
     * 捕获所有被ThreadGroup类的任何线程抛出的非捕捉异常。
     * @param t 线程
     * @param e 异常
     */
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.printf("An exception has been captured\n");
        System.out.printf("Thread: %s\n", t.getName());
        System.out.printf("Exception: %s: %s\n", e.getClass().getName(), e.getMessage());
    }

    public static void main(String[] args) {
        ThreadGroup group = new ExThreadGroup("exception");
        ExceptionThread task = new ExceptionThread();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(group, task);
            thread.start();
        }
    }
}
