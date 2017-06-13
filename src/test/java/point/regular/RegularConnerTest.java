package point.regular;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

/**
 * Created by Vigor on 2017/2/27.
 * 正则表达式练习
 */
public class RegularConnerTest {
    private static RegularConner conner = new RegularConner();

    @Test
    public void isValidFormat() throws Exception {
        assertThat(conner.isValidFormat("938", "[0-9]"), is(true));
        assertThat(conner.isValidFormat("93w8", "[0-9]"), is(false));
        assertThat(conner.isValidFormat("wahw@163.com", "[A-Za-z0-9.@_-~#]"), is(true));
        assertThat(conner.isValidFormat("wahw@163.com", "[^A-Za-z0-9.@_-~#]"), is(false));

    }

    @Test
    public void matches() throws Exception {
        // \d  [0-9]
        assertThat(conner.matches("121", "\\d+"), is(true));
        assertThat(conner.matches("938-00-9876", "\\d{3}-\\d{2}-\\d{4}"), is(true));

        // \D  [^0-9]
        assertThat(conner.matches("122", "\\D+"), is(false));

        // \w  [A-Z0-9]
        assertThat(conner.matches("123", "\\w+"), is(true));

        // \W  [^A-Z0-9]
        assertThat(conner.matches("124", "\\W+"), is(false));

        // \s  [\t\n\r\f]
        assertThat(conner.matches(" ", "\\s+"), is(true));

        // \S  [^\t\n\r\f]
        assertThat(conner.matches(" ", "\\S+"), is(false));

        // . 匹配所有字符
        assertThat(conner.matches("928", "9.8"), is(true));
        assertThat(conner.matches("9a28", "9.+8"), is(true));

        // 方括号限定范围，只匹配单个字符。
        assertThat(conner.matches("9a38", "9[a3]+8"), is(true));

        // 或操作符
        assertThat(conner.matches("9338", "9(a|33)8"), is(true));
        assertThat(conner.matches("9a8", "9(a|33)8"), is(true));

        // + 左边的字符出现1次或多次
        assertThat(conner.matches("938", "[0-9]+"), is(true));

        // ? 左边的字符出现0次或1次
        assertThat(conner.matches("938", "[0-9]?"), is(false));
        assertThat(conner.matches("a", "[a-z][0-9]?"), is(true));

        // * 左边的字符出现0次或多次
        assertThat(conner.matches("938", "[0-9]*"), is(true));
        assertThat(conner.matches("a", "[a-z][0-9]?"), is(true));

        // {n,m} 左边的字符出现出现n~m次
        assertThat(conner.matches("938", "[0-9]{1,2}"), is(false));
        assertThat(conner.matches("938", "[0-9]{1,3}"), is(true));

        // {n} 左边的字符出现出现n次
        assertThat(conner.matches("938-00-9876", "[0-9]{3}-[0-9]{2}-[0-9]{4}"), is(true));

        // ^ 在括号内是否符号
        assertThat(conner.matches("a938-00-9876", "[^x][0-9]{3}-[0-9]{2}-[0-9]{4}"), is(true));

        // 圆括号和空白符号/s,可以利用 oro api 取值。
        assertThat(conner.matches("June 26, 1967", "([A-Za-z]+)\\s+[0-9]{1,2},\\s*[0-9]{4}"), is(true));

        // 电话号码检查
        assertThat(conner.matches("13849949984", "^((13[0-9])|(15[0-3,5-9]))[0-9]{8}$"), is(true));
        assertThat(conner.matches("15649949984", "^((13\\d)|(15[^4, \\D]))\\d{8}$"), is(true));
        assertThat(conner.matches("15449949984", "^((13[0-9])|(15[0-3,5-9]))\\d{8}$"), is(false));
    }

    @Test
    public void lookingAt() throws Exception {
        // lookingAt()对前面的字符串进行匹配。
        assertTrue(conner.lookingAt("123abcde123", "[0-9]+"));
        assertFalse(conner.lookingAt("123abcde123", "[a-z]+"));
    }

    @Test
    public void find() throws Exception {
        assertThat(conner.find("123abcde456f7", "[0-9]+"), is(true));
    }

    @Test
    public void split() throws Exception {
        String[] expected = {"111","222","333"};
        assertArrayEquals(expected, conner.split("111&222|333", "[&|]"));
    }

    @Test
    public void replace() throws Exception {
        assertEquals("*d3i*3*d", conner.replace("   9d33i830d", "[0-9]"));
        assertThat(conner.replace("9d3i830d", "[0-9]"), not("*d*i***d"));
        assertThat(conner.replace("9d3i830d", "[0-9]"), notNullValue());
    }

    @Test
    public void reset() throws Exception {
        assertThat(conner.reset("1234kdkks", "[0-9]+"), is("567"));
        assertThat(conner.reset("abcd333", "[a-z]+"), is(""));
    }

    @Test
    public void replaceOneA() throws Exception {
        assertThat(conner.replaceOneA("abcd/A", "([^/]|^)/A", "$1new"), is("abcdnew"));
        assertThat(conner.replaceOneA("abcd//A", "([^/]|^)/A", "$1new"), is("abcd//A"));
        assertThat(conner.replaceOneA("/A", "([^/]|^)/A", "$1new"), is("new"));
        assertThat(conner.replaceOneA("abcd/A", "(?<!/)/A", "new"), is("abcdnew"));
        assertThat(conner.replaceOneA("abcd//A", "(?<!/)/A", "new"), is("abcd//A"));
        assertThat(conner.replaceOneA("/A", "(?<!/)/A", "new"), is("new"));

    }
}