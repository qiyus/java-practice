package pattern.visitor;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Vigor on 2017/7/16.
 * 测试XML
 */
public class TextExtractorTest {
    @Test
    public void extractor() throws Exception {
        StringBuffer xml = new StringBuffer();
        xml.append("<HTML>");
        xml.append("<BODY>");
        xml.append("Hello, and welcome to my web page.");
        xml.append("<A HREF='http://industrial.com'>");
        xml.append("<IMG SRC='http://industrial.com/img/12.gif'>");
        xml.append("</A>");
        xml.append("</BODY>");
        xml.append("</HTML>");

        HtmlTag htmlTag = new HtmlTag();
        BodyTag bodyTag = new BodyTag();
        bodyTag.addValue("Hello, and welcome to my web page.");
        LinkTag linkTag = new LinkTag();
        linkTag.addAttributes(new Attribute("HREF", "http://industrial.com"));
        ImgTag imgTag = new ImgTag();
        imgTag.addAttributes(new Attribute("SRC", "http://industrial.com/img/12.gif"));

        linkTag.add(imgTag);
        bodyTag.add(linkTag);
        htmlTag.add(bodyTag);

        TextExtractor extractor = new TextExtractor();
        assertThat(xml.toString(), equalTo(extractor.extractor(htmlTag)));
    }

}