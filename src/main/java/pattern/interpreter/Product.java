package pattern.interpreter;

import java.awt.Color;

/**
 * Created by Vigor on 2017/7/15.
 * 产品类
 */
public class Product {
    private final String productId;
    private final String name;
    private final Color color;
    private final float price;
    private final ProductSize size;

    public Product(String productId, String name, Color color, float price, ProductSize size) {
        this.productId = productId;
        this.name = name;
        this.color = color;
        this.price = price;
        this.size = size;
    }

    public Color getColor() {
        return color;
    }

    public float getPrice() {
        return price;
    }

    public String getId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public ProductSize getSize() {
        return size;
    }
}
