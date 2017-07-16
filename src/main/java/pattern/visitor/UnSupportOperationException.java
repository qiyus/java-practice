package pattern.visitor;

/**
 * Created by Vigor on 2017/7/16.
 * 不支持的操作
 */
class UnSupportOperationException extends Exception {
    /**
     * 不支持的操作。
     */
    public UnSupportOperationException() {
        super();
        System.out.print("不支持的操作。");
    }
}
