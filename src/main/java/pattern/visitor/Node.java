package pattern.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vigor on 2017/7/16.
 * 组件类
 */
abstract class Node {
    /**
     * 属性列表
     */
    private final List<Attribute> attributes = new ArrayList<>();

    /**
     * 为节点追加属性
     * @param attribute 属性
     */
    public void addAttributes(Attribute attribute) {
        attributes.add(attribute);
    }

    /**
     * 从节点取得属性
     * @return 属性
     */
    public List<Attribute> getAttributes() {
        return attributes;
    }

    /**
     * 追加子节点
     * @param node 子节点
     * @throws UnSupportOperationException 无子节点时抛出异常。
     */
    public void add(Node node) throws UnSupportOperationException {
        throw new UnSupportOperationException();
    }

    /**
     * 取得子节点列表
     * @return 子节点列表
     * @throws UnSupportOperationException 无子节点是抛出异常。
     */
    public List<Node> getChild() throws UnSupportOperationException {
        throw new UnSupportOperationException();
    }

    /**
     * 方法的内联化处理。
     * @param extractor xml提取器
     * @return xml
     */
    public abstract String accept(TextExtractor extractor);
}
