package point.reflect.proxy;

/**
 * Created by Vigor on 2017/2/24.
 * 设置名字和资历
 */
public class Postman implements People{
    private final String name;
    private final int seniority;

    public Postman(String name, int seniority) {
        this.name = name;
        this.seniority = seniority;
    }

    @Override
    public void printBaseInfo() {
        System.out.println("My name's " + name + ".");
        System.out.println("I have been working for " + seniority + " years.");
    }
}
