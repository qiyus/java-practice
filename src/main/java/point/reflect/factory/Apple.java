package point.reflect.factory;

/**
 * Created by Vigor on 2017/2/24.
 */
public class Apple implements Fruit {
    @Override
    public void eat() {
        System.out.println(this.getClass().getName());
    }
}
