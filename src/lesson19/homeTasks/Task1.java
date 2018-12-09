package lesson19.homeTasks;

//    Написать рефлексивный toString():
//        public static String toString(Object o)
//
//    Метод принимает любой объект и формирует строку на основании его полей без необходимости переопределять метод toString() объекта.
//    Добавить аннотацию @Exclude для полей, которые не должны быть включены в результирующую строку.
//
//    Методы, которые могут понадобиться:
//        Object.getClass()
//
//        Class.getDeclaredFields()
//        Class.getSimpleName()
//        Class.isPrimitive()
//        Class.isArray()
//
//        Field.getName()
//        Field.getType()
//        Field.setAccessible()
//        Field.get()

import java.io.File;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

public class Task1 {

    public static String toString(Object o, int level, boolean flag, boolean recursion) throws NoSuchFieldException, IllegalAccessException {

        StringBuilder resString = new StringBuilder(o.getClass().getName() + " \n" +
                                                        String.format( "%" + (level*3) + "s", "[") + "\n");

        Field[] declaredFields = o.getClass().getDeclaredFields();
        for (Field field: declaredFields){

            field.setAccessible(true);
            if ((field.getAnnotation(Exclude.class))!= null) continue;

            resString.append(String.format( "%" + (level*3) + "s", ""));
            resString.append("("+ field.getType() + ") ").append(field.getName());

            if (field.getType().isPrimitive()) {
                resString.append(" = ");
                resString.append("\"" + field.get(o) + "\"");
            } else
                if (field.getType().isEnum()) resString.append(" {enum element}");
                else
                    if (field.getType().isInterface());
                    else
                        if (field.getType().isArray()) {

                            resString.append(" = {Arrays elements}");

                        }
                        else {

                        if (!recursion) resString.append(" []");
                        else {

                            field.setAccessible(true);

                            if (field.getType().toString().equals("class sun.misc.Unsafe")) {  //ссылка самого на себя
                                if (flag) return "";
                                else resString.append(toString(field.get(o), ++level, true, recursion));
                            } else
                                resString.append(toString(field.get(o), ++level, false, recursion));
                        }
                    }
            resString.append("\n");

        }
        return resString.append(String.format( "%" + (level*3) + "s", "]")).toString();
    }

    public static String toString(Object o, boolean recursion) throws NoSuchFieldException, IllegalAccessException {
        return toString(o, 1, false, recursion);
    }

    public static String toString(Object o) throws NoSuchFieldException, IllegalAccessException {
        return toString(o, 1, false, false);
    }


    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

        File file = new File("file.txt");
        TempClass tempClass = new TempClass();

        System.out.println(toString(file));
        System.out.println("-----------------------------");
        System.out.println(toString(file, true));
        System.out.println("-----------------------------");
        System.out.println(toString(tempClass));


    }
}


class TempClass{
    int i = 5;

    @Exclude
    private String name = "TempClass";
    File file = new File("45454");
}


@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface Exclude {

}