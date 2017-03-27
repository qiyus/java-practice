package point.thread;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by Vigor on 2017/3/27.
 * 线程执行者（五）运行多个任务并处理第一个结果
 */
public class UserValidator {
    private String name;

    public UserValidator(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean validate(String name, String password) {
        Random random = new Random();
        try {
            TimeUnit.SECONDS.sleep((long) (Math.random()*10));
        } catch (InterruptedException e) {
            System.out.println("Other thread is succeed.");
        }
        return random.nextBoolean();
    }
}
