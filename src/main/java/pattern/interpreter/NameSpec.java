package pattern.interpreter;

/**
 * Created by Vigor on 2017/7/16.
 * 名字检索类
 */
class NameSpec extends Spec {
    private final String name;

    public NameSpec(String name) {
        this.name = name;
    }

    @Override
    boolean isSatisfiedBy(Product product) {
        return product.getName().equals(name);
    }
}
