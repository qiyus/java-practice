package point.generic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Stack;

/**
 * Created by Vigor on 2017/2/23.
 * 泛型练习
 * 带类型参数的类
 */
public class Generic<T> {
    private Stack<String> stack = new Stack<>();
    private T arg;

    public Generic(T arg) {
        this.arg = arg;
    }

    public T getArg() {
        return arg;
    }

    /**
     * 带类型参数方法
     * @param item
     * @param <S>
     */
    public <S> void push(S item) {
        stack.push(String.valueOf(item));
    }

    /**
     * 遍历堆栈
     * @return
     */
    public String getStack() {
        String result = "";
        while (!stack.isEmpty()) {
            result += stack.pop();
        }
        return result;
    }

    /**
     * Class的泛型处理
     * String.class类型代表 Class<String>
     * @param c Class
     * @param n Class对象数
     * @param <E>
     * @return
     */
    public <E> Collection<E> makeList(Class<E> c, int n) {
        Collection<E> collection = new ArrayList<>();
        try {
            for (int i = 0; i < n; i++) {
                E item = c.newInstance();
                collection.add(item);
            }
        }
        catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return collection;
    }
}
