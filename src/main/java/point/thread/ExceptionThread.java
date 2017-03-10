package point.thread;

/**
 * Created by Vigor on 2017/3/10.
 * 线程管理（八）在线程里处理unchecked异常
 */
public class ExceptionThread implements Runnable{
    @Override
    public void run() {
        int number = Integer.parseInt("abc");
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new ExceptionThread());
        thread.setUncaughtExceptionHandler(new ExceptionHandler());
        thread.start();
    }
}
