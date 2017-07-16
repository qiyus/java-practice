package pattern.visitor;

/**
 * Created by Vigor on 2017/7/16.
 * 提取节点XML
 */
interface NodeVisitor {
    /**
     * 输出html tag
     * @param html html tag
     * @return xml
     */
    String visitHtmlTag(HtmlTag html);

    /**
     * 输出body tag
     * @param body body tag
     * @return xml
     */
    String visitBodyTag(BodyTag body);

    /**
     * 输出link tag
     * @param link link tag
     * @return xml
     */
    String visitLinkTag(LinkTag link);

    /**
     * 输出img tag
     * @param img img tag
     * @return xml
     */
    String visitImgTag(ImgTag img);
}
