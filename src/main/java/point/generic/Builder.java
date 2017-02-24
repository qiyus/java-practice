package point.generic;

/**
 * Created by Vigor on 2017/2/23.
 * 泛型的通配符使用,泛型不是协变的。
 */
public class Builder {
    private String result = "";

    public static Builder create() {
        return new Builder();
    }

    public Builder add(Generic<?> generic) {
        result = result + generic.getArg() + " ";
        return this;
    }

    public Builder addNumber(Generic<? extends Number> generic) {
        result = result + generic.getArg() + " ";
        return this;
    }

    public Builder addInteger(Generic<? super Integer> generic) {
        result = result + generic.getArg() + " ";
        return this;
    }

    public String build() {
        return result;
    }
}
