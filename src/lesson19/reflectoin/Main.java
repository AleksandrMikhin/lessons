package lesson19.reflectoin;

import java.lang.reflect.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args)
            throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {

//        каждый загруженный класс имеет соответствующий
//        java.lang.Class объет, который дает доступ к структуре класса

//        У каждого типа есть свой литерал
        System.out.println(String.class);
        System.out.println(int.class);

        Child child = new Child("someChild", 5);
//        строковый литерал объекта
        System.out.println(child.getClass());
//        строковый литерал класса настедника

        Class cls;
        cls = child.getClass();
        System.out.println("child name " + cls.getName());

        cls = cls.getSuperclass();
        System.out.println("child extends " + cls.getName());

//        доступ к компонентам

//        класс объекта
        Class<Child> childClass = Child.class;

//        доступ к полям
        Field[] fields = childClass.getFields();
        System.out.println(Arrays.toString(fields));

//        доступ к declared полям - второй метод
        Field[] declaredFields = childClass.getDeclaredFields();
        System.out.println(Arrays.toString(declaredFields));

//        доступ к методам + родителей
        Method[] methods = childClass.getMethods();
        System.out.println(Arrays.toString(methods));

//        методы класса, включая приватные, без методов родителя
        Method[] declaredMethods = childClass.getDeclaredMethods();
        System.out.println(Arrays.toString(declaredMethods));

//        доступ к конструкторам
        Constructor<?>[] declaredConstructors = childClass.getDeclaredConstructors();
        System.out.println(Arrays.toString(declaredConstructors));

//////////////////////////////////////////////////////////////////
//        доступ к конкретному полю, методу, конструктору

        Field field = childClass.getDeclaredField("name");

//        получить доступ к полю
        field.setAccessible(true);
        field.set(child, "CHILD");
        System.out.println((String) field.get(child));
        System.out.println(child);

//        получить доступ к методу
        Method method = childClass.getDeclaredMethod("getInfo");
        method.setAccessible(true);
        String data = (String) method.invoke(child); // вызов метода в объекту
        System.out.println(data);

//        достук к конструктору
        Constructor<Child> childConstructor = childClass.getDeclaredConstructor(String.class, int.class);
//        создание объекта
        Child child1 = childConstructor.newInstance("Child 1", 3);
        System.out.println(child1);

        boolean isFinal = Modifier.isFinal(field.getModifiers());
        boolean isPrivate = Modifier.isPrivate(field.getModifiers());

        Class<?>[] interfaces = childClass.getInterfaces();

    }
}
