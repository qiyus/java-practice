package pattern.interpreter;

/**
 * Created by Vigor on 2017/7/15.
 * Not检索类
 */
class NotSpec extends Spec {
    private final Spec spec;

    public NotSpec(Spec spec) {
        this.spec = spec;
    }

    @Override
    boolean isSatisfiedBy(Product product) {
        return !spec.isSatisfiedBy(product);
    }
}
