package pattern.interpreter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vigor on 2017/7/15.
 * And检索类
 */
class AndSpec extends Spec {
    private final List<Spec> specs = new ArrayList<>();

    void add(Spec spec) {
        specs.add(spec);
    }

    @Override
    boolean isSatisfiedBy(Product product) {
        boolean isSatisfied = true;
        for (Spec spec : specs) {
            isSatisfied &= spec.isSatisfiedBy(product);
        }
        return isSatisfied;
    }
}
