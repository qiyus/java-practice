package pattern.visitor;

/**
 * Created by Vigor on 2017/7/16.
 * 属性类
 */
class Attribute {
    /**
     * 属性名
     */
    private final String name;

    /**
     * 属性值
     */
    private final String value;

    /**
     * 构造函数
     * @param name 属性名
     * @param value 属性值
     */
    public Attribute(String name, String value) {
        this.name = name;
        this.value = value;
    }

    /**
     * 取key
     * @return key
     */
    public String getName() {
        return name;
    }

    /**
     * 取value
     * @return value
     */
    public String getValue() {
        return value;
    }
}
