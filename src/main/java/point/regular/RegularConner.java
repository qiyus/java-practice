package point.regular;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Vigor on 2017/2/27.
 * 正则表达式练习
 */
public class RegularConner {

    /**
     * 字符有效性检查。
     *
     * @param s     字符串
     * @param regex 表达式
     * @return 匹配成功/失败
     */
    public boolean isValidFormat(String s, String regex) {
        char[] array = s.toCharArray();
        boolean matches = true;
        for (char c : array) {
            String single = String.valueOf(c);
            if (!Pattern.matches(regex, single)) {
                System.out.println("\"" + single + "\"" + "isn't valid format");
                matches = false;
                break;
            }
        }
        return matches;
    }

    /**
     * 对整个字符串进行匹配。
     *
     * @param s     字符串
     * @param regex 表达式
     * @return 匹配成功/失败
     */
    public boolean matches(String s, String regex) {
        // flag 范围可以参照文档。
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        boolean result = matcher.matches();
        if (result) {
            // Group是指用括号括起来的表达式。group(0)表示整个表达式，group(1)表示第一个被括起来的表达式，以此类推。
            System.out.println(matcher.group(0));

            if (matcher.groupCount() >= 1) {
                System.out.println("group 1: " + matcher.group(1));
                System.out.println("start: " + matcher.start(1));
                System.out.println("end: " + matcher.end(1));
            }
        }
        return result;
    }

    /**
     * 对前面的字符串进行匹配。
     *
     * @param s     字符串
     * @param regex 表达式
     * @return 匹配成功/失败
     */
    public boolean lookingAt(String s, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        if (matcher.lookingAt()) {
            System.out.println(matcher.group(0));
            return true;
        }
        return false;
    }

    /**
     * 对字符串进行匹配，匹配的字符串可以在任何位置。
     * @param s 字符串
     * @param regex 表达式
     * @return 匹配成功/失败
     */
    public boolean find(String s, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);

        boolean result = false;
        while (matcher.find()){
            System.out.println(matcher.group(0));
            result = true;
        }
        return result;
    }

    /**
     * 分隔字符串
     * @param s 字符串
     * @param regex 表达式
     * @return 分隔后的字符串
     */
    public String[] split(String s, String regex) {
        Pattern pattern = Pattern.compile(regex);
        String[] result = pattern.split(s);
        for (String r : result) {
            System.out.println(r);
        }
        return result;
    }

    /**
     * 将符合正则表达式的字符串替换为指定字符串。
     * @param s 字符串
     * @param regex 表达式
     * @return 替换后的字符串
     */
    public String replace(String s, String regex) {
        // 删除每行开头部分的所有空格，(?m)打开多行状态。
        String input = s.replaceFirst("\\s+", "");
        input = input.replaceAll("3{2,}", "3");
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        StringBuffer buffer = new StringBuffer();
        while (matcher.find()) {
            if (matcher.group().equals("3")) {
                continue;
            }

            // 将符合表达式的部分替换为*
            matcher.appendReplacement(buffer, "*");
        }

        // 将剩余部分复制到buffer
        matcher.appendTail(buffer);

        return buffer.toString();
    }

    /**
     * 重设matcher
     * @param s 字符串
     * @param regex 表达式
     * @return 重设的字符串。
     */
    public String reset(String s, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        if (matcher.lookingAt()) {
            matcher.reset("567");
        }

        String result = "";
        if (matcher.find()) {
            result = matcher.group();
        }

        return result;
    }

    public String replaceOneA(String s, String pattern, String replacement) {
        return s.replaceAll(pattern, replacement);
    }

}
