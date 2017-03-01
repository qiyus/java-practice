package point.breakpoint;

/**
 * Created by Vigor on 2017/3/1.
 * 1, Evaluate Expression alt + F8
 * 2, Frames
 * 3, Variables
 * 4, 行断点，点击指定行。
 * 5，函数断点，点击在函数定义处，关注方法参数，返回值。
 * 6，条件断点，右键点击行断点。满足条件时程序停止。
 * 7，日志断点, 在断点设置页面取消Suspend, 设置Log输出内容。程序不停止，输出Log。
 * 8，异常断点，在断点设置页面追加（+）异常断点，选择异常类型。
 * 9, 字段断点，在变量定义处设置断点，右键设置"访问"或"写入"停止。
 */
public class BreakPoint {
    private String getField() {
        return field;
    }

    private void setField(String field) {
        this.field = field;
    }

    private String field;

    private BreakPoint(String field) {
        this.field = field;
    }

    private int add(int a, int b) {
        int c = a + b;
        c++;
        return c;
    }

    private int add(String a, String b) throws MyException {
        int result;
        try {
            result = Integer.parseInt(a) + Integer.parseInt(b);
        }
        catch (NumberFormatException e) {
            throw new MyException(e.getMessage());
        }
        return result;
    }

    public static void main(String[] args) {

        try {
            BreakPoint bp = new BreakPoint("field");
            bp.setField("breakpoint");
            bp.setField("1");
            System.out.println(bp.getField());
            bp.setField("2");
            System.out.println(bp.getField());
            System.out.println(bp.add(1,2));
            System.out.println(bp.add(3,4));
            System.out.println(bp.add("1", "b"));
            System.out.println(bp.add("a", "e"));
        } catch (MyException e) {
            e.printStackTrace();
        }
    }
}
