package pattern.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vigor on 2017/7/16.
 * Body
 */
class BodyTag extends Node{
    /**
     * 子节点列表
     */
    private final List<Node> child = new ArrayList<>();

    /**
     * 表示内容
     */
    private String value;

    /**
     * 追加子节点
     * @param node 子节点
     */
    @Override
    public void add(Node node)  {
        child.add(node);
    }

    /**
     * 返回子节点列表
     * @return 子节点列表
     */
    @Override
    public List<Node> getChild() {
        return child;
    }

    /**
     * 函数内联化
     * @param extractor xml提取器
     * @return xml
     */
    @Override
    public String accept(TextExtractor extractor) {
        return extractor.visitBodyTag(this);
    }

    /**
     * 追加Value
     * @param value 表示内容
     */
    public void addValue(String value) {
        this.value = value;
    }

    /**
     * 取得Value
     * @return 表示内容
     */
    public String getValue() {
        return value;
    }
}
