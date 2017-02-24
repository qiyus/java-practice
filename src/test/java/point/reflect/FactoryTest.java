package point.reflect;

import org.junit.Test;
import point.reflect.factory.Apple;
import point.reflect.factory.Factory;
import point.reflect.factory.Fruit;
import point.reflect.factory.Orange;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

/**
 * Created by Vigor on 2017/2/24.
 */
public class FactoryTest {
    @Test
    public void createFruit() throws Exception {
        Fruit apple = Factory.createFruit("point.reflect.factory.Apple");
        assertThat(apple, instanceOf(Apple.class));
        Fruit orange = Factory.createFruit("point.reflect.factory.Orange");
        assertThat(orange, instanceOf(Orange.class));
    }
}