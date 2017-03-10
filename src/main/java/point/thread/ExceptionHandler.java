package point.thread;

/**
 * Created by Vigor on 2017/3/10.
 * 线程管理（八）在线程里处理unchecked异常
 */
public class ExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.printf("An exception has been captured\n");
        System.out.printf("Thread: %s\n", t.getName());
        System.out.printf("Exception: %s: %s\n", e.getClass().getName(), e.getMessage());
    }
}
