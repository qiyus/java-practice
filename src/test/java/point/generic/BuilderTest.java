package point.generic;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by Vigor on 2017/2/23.
 */
public class BuilderTest {
    @Test
    public void build() throws Exception {
        Generic<String> name = new Generic<>("John");
        Generic<Integer> age = new Generic<>(12);
        Generic<Number> tall = new Generic<>(165);
        String info = Builder.create()
                .add(name)
                .add(age)
                .addNumber(age)
                .addInteger(tall)
                .build();
        assertThat(info, is("John 12 12 165 "));
    }

}