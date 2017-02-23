package pattern.singleton;

/**
 * Created by Vigor on 2017/2/22.
 * 利用内部类实现单例。
 */
public class InnerClassSingleton {

    public static InnerClassSingleton getInstance() {
        return Singleton.instance;
    }

    private InnerClassSingleton() {
    }

    private static class Singleton {
        public static final InnerClassSingleton instance = new InnerClassSingleton();
    }
}
