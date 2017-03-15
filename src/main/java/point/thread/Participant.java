package point.thread;

import java.util.concurrent.TimeUnit;

/**
 * Created by Vigor on 2017/3/15.
 */
public class Participant implements Runnable {
    VideoConference conference;

    public Participant(VideoConference conference) {
        this.conference = conference;
    }

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep((long) (Math.random() * 10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        conference.arrive(Thread.currentThread().getName());
    }
}
