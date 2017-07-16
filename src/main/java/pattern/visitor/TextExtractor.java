package pattern.visitor;

import java.util.List;

/**
 * Created by Vigor on 2017/7/16.
 * XML提取器。
 */
class TextExtractor implements NodeVisitor{

    /**
     * 提取xml
     * @param node xml节点
     * @return xml
     */
    public String extractor(Node node) {
        StringBuilder xml = new StringBuilder();
        xml.append(node.accept(this));
        return xml.toString();
    }

    /**
     * 提取img节点
     * @param img img tag
     * @return xml
     */
    @Override
    public String visitImgTag(ImgTag img) {
        StringBuilder xml = new StringBuilder();
        xml.append("<IMG");
        List<Attribute> attributes = img.getAttributes();
        for (Attribute attribute : attributes) {
            xml.append(extractorAttribute(attribute));
        }
        xml.append(">");
        return xml.toString();
    }

    /**
     * 提取link 节点
     * @param link link tag
     * @return xml
     */
    @Override
    public String visitLinkTag(LinkTag link) {
        StringBuilder xml = new StringBuilder();
        xml.append("<A");
        List<Attribute> attributes = link.getAttributes();
        for (Attribute attribute : attributes) {
            xml.append(extractorAttribute(attribute));
        }
        xml.append(">");
        List<Node> children = link.getChild();
        for (Node child : children) {
            xml.append(extractor(child));
        }
        xml.append("</A>");
        return xml.toString();
    }

    /**
     * 提取body 节点
     * @param body body tag
     * @return xml
     */
    @Override
    public String visitBodyTag(BodyTag body) {
        StringBuilder xml = new StringBuilder();
        xml.append("<BODY");
        List<Attribute> attributes = body.getAttributes();
        for (Attribute attribute : attributes) {
            xml.append(extractorAttribute(attribute));
        }
        xml.append(">");
        xml.append(body.getValue());
        List<Node> children = body.getChild();
        for (Node child : children) {
            xml.append(extractor(child));
        }
        xml.append("</BODY>");
        return xml.toString();
    }

    /**
     * 提取html节点
     * @param html html tag
     * @return xml
     */
    @Override
    public String visitHtmlTag(HtmlTag html) {
        StringBuilder xml = new StringBuilder();
        xml.append("<HTML>");
        List<Node> children = html.getChild();
        for (Node child : children) {
            xml.append(extractor(child));
        }
        xml.append("</HTML>");
        return xml.toString();
    }

    /**
     * 拼接属性字符串
     * @param attribute 属性
     * @return 属性字符串
     */
    private String extractorAttribute(Attribute attribute) {
        return " " + attribute.getName() + "='" + attribute.getValue() + "'";
    }
}
