package point.thread;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Created by Vigor on 2017/3/6.
 * 并发编程练习
 * 线程管理（四）操作线程的中断机制
 */
public class FileSearcher implements Runnable {
    private final String searchPath;
    private final String fileName;

    private FileSearcher(String searchPath, String fileName) {
        this.searchPath = searchPath;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        File file = new File(searchPath);
        if (file.isDirectory()) {
            try {
                directoryProcess(file);
            } catch (InterruptedException e) {
                System.out.printf("%s: The search has been interrupted.\n", Thread.currentThread().getName());
            }
        }
    }

    /**
     * 遍历目录
     *
     * @param file 目录
     * @throws InterruptedException 异常终止
     */
    private void directoryProcess(File file) throws InterruptedException {
        File files[] = file.listFiles();
        if (files == null) {
            return;
        }

        for (File f : files) {
            if (f.isDirectory()) {
                directoryProcess(f);
            } else {
                fileProcess(f);
            }
        }
    }

    /**
     * 输出文件路径
     *
     * @param file 文件
     * @throws InterruptedException 异常终止
     */
    private void fileProcess(File file) throws InterruptedException {
        if (file.getName().equals(fileName)) {
            System.out.printf("%s : %s \n", Thread.currentThread().getName(), file.getAbsolutePath());
        }

        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new FileSearcher("/Users/newuser/Downloads/", "english.rtf"));
        thread.start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt();
    }
}
