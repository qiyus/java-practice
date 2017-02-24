package point.reflect.factory;

/**
 * Created by Vigor on 2017/2/24.
 */
public class Factory {
    public static Fruit createFruit(String className) {
        try {
            return (Fruit) Class.forName(className).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
