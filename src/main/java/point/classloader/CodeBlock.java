package point.classloader;

/**
 * Created by Vigor on 2017/3/31.
 * 代码块执行时机验证。
 */
public class CodeBlock {
    private static String name = "static variable";

    {
        name = "test initialize.";
    }

    public static String getName() {
        return name;
    }

    public static void main(String[] args) {
        System.out.println(CodeBlock.getName());
    }
}
