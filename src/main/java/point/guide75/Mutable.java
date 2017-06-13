package point.guide75;

/**
 * Created by Vigor on 2017/4/14.
 */
public class Mutable {
    private int[] array = new int[10];

    public Mutable() {
        for (int i = 0; i < 10; i++) {
            array[i] = i;
        }
    }

    public int[] getArray() {
        return array;
    }

    public static void main(String[] args) {
        char alpha = 'a';
        int i = 0;

        boolean exp = true;
        System.out.println(exp ? alpha : 0);
        System.out.println(exp ? alpha : i);
    }
}
