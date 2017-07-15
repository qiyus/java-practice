package pattern.interpreter;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Vigor on 2017/7/15.
 * 产品检索类
 */
public class ProductFinder {
    /**
     * 产品仓库
     */
    private final ProductRepository repository;

    public ProductFinder(ProductRepository productRepository) {
        this.repository = productRepository;
    }

    /**
     * 检索指定颜色的产品
     * @param color 指定的颜色。
     * @return 检索到的产品列表。
     */
    public List<Product> findByColor(Color color) {
        ColorSpec spec = new ColorSpec(color);
        return findBySpec(spec);
    }

    /**
     * 检索指定的颜色和指定价格以下的产品。
     * @param price 指定的价格
     * @param color 指定的颜色
     * @return 检索到的产品列表
     */
    public List<Product> findByBelowPriceAndColor(float price, Color color) {
        BelowPriceSpec belowPriceSpec = new BelowPriceSpec(price);
        ColorSpec colorSpec = new ColorSpec(color);
        AndSpec spec = new AndSpec();
        spec.add(belowPriceSpec);
        spec.add(colorSpec);
        return findBySpec(spec);
    }

    /**
     * 检索指定Id的产品列表
     * @param id 指定的产品Id
     * @return 检索到的产品列表
     */
    public List<Product> findById(String id) {
        IdSpec spec = new IdSpec(id);
        return findBySpec(spec);
    }

    /**
     * 检索指定颜色外的产品
     * @param color 指定的颜色
     * @return 检索到的产品列表
     */
    public List<Product> findByNotColor(Color color) {
        ColorSpec spec = new ColorSpec(color);
        NotSpec notSpec = new NotSpec(spec);
        return findBySpec(notSpec);
    }



    /**
     * 按照指定的检索器进行检索
     * @param spec 检索器
     * @return 检索结果
     */
    private List<Product> findBySpec(Spec spec) {
        List<Product> foundProducts = new ArrayList<>();
        Iterator products = repository.iterator();
        while (products.hasNext()) {
            Product product = (Product) products.next();
            if (spec.isSatisfiedBy(product)) {
                foundProducts.add(product);
            }
        }
        return foundProducts;
    }

    /**
     * 按照指定的名字检索
     * @param name 指定的名字
     * @return 检索到的产品
     */
    public List<Product> findByName(String name) {
        Spec spec = new NameSpec(name);
        return findBySpec(spec);
    }

    /**
     * 按照指定的Size检索
     * @param size 指定的Size
     * @return 检索到的产品
     */
    public List<Product> findBySize(ProductSize size) {
        Spec spec = new SizeSpec(size);
        return findBySpec(spec);
    }
}
