package pattern.interpreter;

/**
 * Created by Vigor on 2017/7/15.
 * 小于指定价格检索类
 */
class BelowPriceSpec extends Spec{
    private final float price;

    public BelowPriceSpec(float price) {
        this.price = price;
    }

    @Override
    boolean isSatisfiedBy(Product product) {
        return product.getPrice() < price;
    }
}
