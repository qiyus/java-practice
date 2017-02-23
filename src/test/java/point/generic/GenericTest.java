package point.generic;

import org.junit.Test;

import java.util.Collection;
import java.util.Objects;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by Vigor on 2017/2/23.
 * 测试泛型
 */
public class GenericTest {
    @Test
    public void getNameOfString() throws Exception {
        Generic<String> generic = new Generic<>("John");
        assertThat(generic.getArg(), is("John"));
    }

    @Test
    public void getNameOfNumber() throws Exception {
        Generic<Integer> generic = new Generic<>(828737);
        assertThat(generic.getArg(), is(828737));
    }

    @Test
    public void getStack() throws Exception {
        Generic<String> generic = new Generic<>("method");
        generic.push("1");
        generic.push("2");
        generic.push("3");
        generic.push("4");
        assertThat(generic.getStack(), is("4321"));
    }

    @Test
    public void getMakeList() throws Exception {
        Generic<String> generic = new Generic<>("class");
        Collection<String> list = generic.makeList(String.class, 3);
        assertThat(list.size(), is(3));
    }
}