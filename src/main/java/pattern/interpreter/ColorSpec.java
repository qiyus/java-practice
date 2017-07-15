package pattern.interpreter;

import java.awt.Color;

/**
 * Created by Vigor on 2017/7/15.
 * 颜色检索类
 */
class ColorSpec extends Spec {
    private final Color colorToFind;

    public ColorSpec(Color colorToFind) {
        this.colorToFind = colorToFind;
    }

    @Override
    boolean isSatisfiedBy(Product product) {
        return product.getColor().equals(colorToFind);
    }
}
