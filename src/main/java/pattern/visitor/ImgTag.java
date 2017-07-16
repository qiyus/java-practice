package pattern.visitor;

/**
 * Created by Vigor on 2017/7/16.
 * IMG
 */
class ImgTag extends Node{

    /**
     * 内联化
     */
    @Override
    public String accept(TextExtractor extractor) {
        return extractor.visitImgTag(this);
    }
}
