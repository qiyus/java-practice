package point.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Vigor on 2017/3/27.
 * 线程执行者（五）运行多个任务并处理第一个结果
 */
public class TaskValidator implements Callable<String> {
    private UserValidator validator;
    private String user;
    private String password;

    public TaskValidator(UserValidator validator, String user, String password) {
        this.validator = validator;
        this.user = user;
        this.password = password;
    }

    @Override
    public String call() throws Exception {
        if (!validator.validate(user, password)) {
            System.out.printf("%s: The user has not been found\n", validator.getName());
            throw new Exception("Error validating user");
        }
        System.out.printf("%s: The user has been found\n", validator.getName());
        return validator.getName();
    }

    public static void main(String[] args) {
        final String username = "Jones";
        final String password = "Sally";

        UserValidator ldapValidator = new UserValidator("LDAP");
        UserValidator dbValidator = new UserValidator("DataBase");
        TaskValidator ldapTask = new TaskValidator(ldapValidator, username, password);
        TaskValidator dbTask = new TaskValidator(dbValidator, username, password);

        List<TaskValidator> validators = new ArrayList<>();
        validators.add(ldapTask);
        validators.add(dbTask);
        ExecutorService executorService = Executors.newCachedThreadPool();
        try {
            System.out.printf("%s validate succeed.\n", executorService.invokeAny(validators));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            System.out.println("ExecutionException: validate failed.");
        }
        executorService.shutdown();
    }
}
