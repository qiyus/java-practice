package pattern.interpreter;

import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Vigor on 2017/7/15.
 * 重构与模式 第8章解释器模式示例。
 */
public class ProductFinderTest {
    private ProductFinder finder;

    private Product fireTruck = new Product("0001", "fire truck", Color.RED, 8.0f, ProductSize.MEDIUM);
    private Product barbie = new Product("0002", "barbie", Color.YELLOW, 18.0f, ProductSize.SMALL);
    private Product frisbee = new Product("0003", "frisbee", Color.GREEN, 11.0f, ProductSize.LARGE);
    private Product baseball = new Product("0004", "baseball", Color.WHITE, 38.0f, ProductSize.NOT_APPLICABLE);
    private Product porsche = new Product("0005", "porsche", Color.RED, 10.0f, ProductSize.NOT_APPLICABLE);

    @Before
    public void setUp() throws Exception {
        finder = new ProductFinder(createProductRepository());
    }

    private ProductRepository createProductRepository() {
        ProductRepository repository = new ProductRepository();
        repository.add(fireTruck);
        repository.add(barbie);
        repository.add(frisbee);
        repository.add(baseball);
        repository.add(porsche);
        return repository;
    }

    @Test
    public void findByColor() throws Exception {
        List<Product> products = finder.findByColor(Color.RED);
        assertThat(products.size(), is(2));
    }

    @Test
    public void findById() throws Exception {
        List<Product> products = finder.findById("0003");
        assertThat(products, hasItem(frisbee));
    }

    @Test
    public void findByBelowPriceAndColor() throws Exception {
        List<Product> products = finder.findByBelowPriceAndColor(10.0f, Color.RED);
        assertThat(products, hasItem(fireTruck));
    }

    @Test
    public void findByNotColor() throws Exception {
        List<Product> products = finder.findByNotColor(Color.YELLOW);
        assertThat(products, not(hasItem(barbie)));
    }

    @Test
    public void findByName() throws Exception {
        List<Product> products = finder.findByName("baseball");
        assertThat(products, hasItem(baseball));
    }

    @Test
    public void findBySize() throws Exception {
        List<Product> products = finder.findBySize(ProductSize.LARGE);
        assertThat(products, hasItem(frisbee));
    }

}