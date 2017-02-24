package point.reflect.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by Vigor on 2017/2/24.
 * 测试
 */
public class InvocationHandlerTestDrive {
    public static void main(String[] args) {
        People postman = new Postman("Peter", 23);
        proxyPrintBaseInfo(postman);
        People student = new Student("Joe", 10);
        proxyPrintBaseInfo(student);
    }

    private static void proxyPrintBaseInfo(People people) {
        InvocationHandler handler = new PeopleInvocationHandler(people);
        People proxy = (People) Proxy.newProxyInstance(people.getClass().getClassLoader(),
                people.getClass().getInterfaces(), handler);
        proxy.printBaseInfo();
    }
}
