package point.reflect.proxy;

/**
 * Created by Vigor on 2017/2/24.
 * 指定年龄和名字
 */
public class Student implements People {
    private final String name;
    private final int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public void printBaseInfo() {
        System.out.println("My name's " + name + ".");
        System.out.println("I'm " + age + ".");
    }
}
