package point.thread;

import java.util.Random;

/**
 * Created by Vigor on 2017/3/16.
 * 线程同步工具（四）在同一个点同步任务
 */
public class MatrixMock {
    private int data[][];
    private int size;

    public MatrixMock(int size, int length, int search) {
        int counter = 0;
        this.size = size;
        data = new int[size][length];
        Random random = new Random();

        // 用随机数字填充矩阵。每生成一个数字就与要查找的数字对比，如果相等，就增加counter值。
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < length; j++) {
                data[i][j] = random.nextInt(10);
                if (data[i][j] == search) {
                    counter++;
                }
            }
        }

        System.out.printf("initial counter: %d\n", counter);
    }

    public int getSize() {
        return size;
    }

    public int[] getRow(int row) {
        if ((row >= 0) && (row < data.length)) {
            return data[row];
        }
        return null;
    }
}
