package pattern.interpreter;

/**
 * Created by Vigor on 2017/7/15.
 * Id检索类
 */
class IdSpec extends Spec{
    private final String id;

    public IdSpec(String id) {
        this.id = id;
    }

    @Override
    boolean isSatisfiedBy(Product product) {
        return product.getId().equals(id);
    }
}
