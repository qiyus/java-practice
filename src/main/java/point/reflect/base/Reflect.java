package point.reflect.base;

import java.lang.reflect.*;

/**
 * Created by Vigor on 2017/2/23.
 * 反射练习
 */
public class Reflect extends Parent implements IReflect {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException, NoSuchFieldException {

        System.out.println("Class 对象取得");
        System.out.println((new Reflect("class")).getClass().getName());
        System.out.println(Class.forName("point.reflect.base.Reflect").getName());
        System.out.println(Reflect.class.getName());

        System.out.println("取得父类");
        Class<Reflect> clazz = (Class<Reflect>) Class.forName("point.reflect.base.Reflect");
        System.out.println(clazz.getSuperclass().getName());

        System.out.println("获取所有的接口");
        Class<?> ifs[] = clazz.getInterfaces();
        for (Class<?> i : ifs) {
            System.out.println(i.getName());
        }

        System.out.println("默认构造方法实例化");
        Reflect instance = clazz.newInstance();
        instance.printName();

        System.out.println("取得全部的构造函数 使用构造函数实例化");
        Constructor<?> cons[] = clazz.getConstructors();
        for (Constructor<?> con : cons) {
            if (con.getParameterCount() == 1 && con.getParameterTypes()[0].equals(String.class)) {
                Reflect instance1 = (Reflect) con.newInstance("constructor");
                instance1.printName();
            }
        }

        System.out.println("取得本类的全部属性");
        printField(clazz.getDeclaredFields());

        System.out.println("取得实现的接口或者父类的属性");
        printField(clazz.getFields());

        System.out.println("获取某个类的全部方法");
        Method methods[] = clazz.getMethods();
        for (Method m : methods) {
            System.out.print(Modifier.toString(m.getModifiers()));
            System.out.print(" " + m.getReturnType());
            System.out.print(" " + m.getName());
            System.out.print("(");
            Parameter parameters[] = m.getParameters();
            String methodParameter = "";
            for (Parameter p : parameters) {
                methodParameter += p.getType() + " " + p.getName() + ", ";
            }
            if (methodParameter.length() > 0) {
                methodParameter = " " + methodParameter.substring(0, methodParameter.length() - 2) + " ";
            }
            System.out.print(methodParameter);
            System.out.print(") ");

            String methodException = "";
            Class<?> exceptions[] = m.getExceptionTypes();
            if (exceptions.length > 0) {
                System.out.print("throw ");
                for (Class<?> e : exceptions) {
                    methodException += e.getName() + ", ";
                }
                System.out.print(methodException.substring(0, methodException.length() - 2));
            }
            System.out.println(methodException);
        }

        System.out.println("通过反射机制调用某个类的方法");
        Method printName = clazz.getMethod("printName");
        printName.invoke(clazz.newInstance());

        Method printInfo = clazz.getMethod("printInfo", String.class, int.class);
        printInfo.invoke(clazz.newInstance(), "invoke", 3);

        System.out.println("通过反射机制操作某个类的属性");
        Reflect instance2 = clazz.newInstance();
        Field field = clazz.getDeclaredField("name");
        field.setAccessible(true);
        field.set(instance2, "modify property");
        instance2.printInfo((String) field.get(instance2), 1);
        instance2.printName();


    }

    private String name;

    public Reflect() {
        this.name = "default";
    }

    public Reflect(String name) {
        this.name = name;
    }

    private static void printField(Field fields[]) {
        for (Field f : fields) {

            // 权限修饰符
            System.out.print(Modifier.toString(f.getModifiers()));

            // 类型
            Class<?> fieldType = f.getType();
            System.out.print(" " + fieldType.getName());

            // 名称
            System.out.println(" " + f.getName());
        }
    }

    @Override
    public void printName() {
        System.out.println(name);
    }

    @Override
    public void printInfo(String info, int n) {
        for (int i = 0; i < n; i++) {
            System.out.println(info);
        }
    }
}
