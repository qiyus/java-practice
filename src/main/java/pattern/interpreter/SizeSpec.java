package pattern.interpreter;

/**
 * Created by Vigor on 2017/7/16.
 * size检索类
 */
class SizeSpec extends Spec{
    private final ProductSize size;

    public SizeSpec(ProductSize size) {
        this.size = size;
    }

    @Override
    boolean isSatisfiedBy(Product product) {
        return product.getSize().equals(size);
    }
}
