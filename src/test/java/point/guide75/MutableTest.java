package point.guide75;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by Vigor on 2017/4/14.
 */
public class MutableTest {
    private static Mutable mutable = new Mutable();

    @Test
    public void setArray() throws Exception {
        int[] array = mutable.getArray();
        array[0] = 10;
        assertThat(10, is(mutable.getArray()[0]));
    }
}