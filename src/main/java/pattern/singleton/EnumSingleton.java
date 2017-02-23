package pattern.singleton;

/**
 * Created by Vigor on 2017/2/22.
 * 利用枚举实现单例
 */
public class EnumSingleton {

    public static EnumSingleton getInstance() {
        return Singleton.INSTANCE.instance;
    }

    private EnumSingleton() {
    }

    private enum Singleton {
        INSTANCE;

        private EnumSingleton instance = new EnumSingleton();
    }
}
