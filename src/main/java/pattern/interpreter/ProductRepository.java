package pattern.interpreter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Vigor on 2017/7/15.
 * 产品仓库
 */
class ProductRepository {
    private final List<Product> products = new ArrayList<>();

    public void add(Product product) {
        products.add(product);
    }

    public Iterator iterator() {
        return products.iterator();
    }
}
