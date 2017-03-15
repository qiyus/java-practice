package point.thread;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Vigor on 2017/3/15.
 * 线程同步工具（三）等待多个并发事件完成
 */
public class VideoConference implements Runnable {
    private CountDownLatch controller;

    public VideoConference(int number) {
        controller = new CountDownLatch(number);
    }

    public void arrive(String name) {
        System.out.printf("%s has arrived.\n", name);
        controller.countDown();
    }

    @Override
    public void run() {
        try {
            controller.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("VideoConference: All the participants have come\n");
    }

    public static void main(String[] args) {
        VideoConference conference = new VideoConference(10);
        Thread thread = new Thread(conference);
        thread.start();

        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new Participant(conference));
            t.start();
        }
    }
}
