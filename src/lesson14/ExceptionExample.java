package lesson14;

import java.util.Arrays;

public class ExceptionExample {

    public static void main(String[] args) {

        int[] arr = new int[5];
//        arr[10] = 23;  // java.lang.ArrayIndexOutOfBoundsException
        System.out.println(Arrays.toString(arr));

        String str = null;
//        str.equals("chbjfyj");  //java.lang.NullPointerException

        int a = 9, b = 0;
//        System.out.println(a/b);  //java.lang.ArithmeticException: / by zero

//        Integer.parseInt("abcd");  //java.lang.NumberFormatException

        //выбросить свое исключение
//        throw new NumberFormatException("сообщение");

         //обработка исключений
//        try-catch

        Object data = "42";
//        Integer intData = (Integer) data;  //ClassCastExсeption

        try {
            Integer intData = (Integer) data;  //ClassCastExeption
            System.out.println("code after ClassCastExсeption");
        }
//        catch (ClassCastException e) {
//            System.out.println("exсeption - " + e.getMessage());
//        }

// обработать несколько видов исключений - старый метод : один блок try, несколько блоков catch друг за другом

        // можно отлавливать все Runtime
//        catch (RuntimeException e){
//            System.out.println("exсeption - " + e.getMessage());
//        }

        catch (ClassCastException | ArrayIndexOutOfBoundsException e){
            System.out.println("exсeption - " + e.getMessage());
        }

//    блок finally - будет выполнятся в любом случае (например закрытие соединений с файлом)
        finally {
            System.out.println("finnaly");
        }


        // checked exception - обработка в блоке try-catch либо на уровень выше
//        throw new Exception("checked exception"); - не пойдет

//        обработка в блоке try-catch либо на уровень выше
        try {
            throw new Exception("checked exception");
        } catch (Exception e) {
            e.printStackTrace();
        }

//      Обработка на уровень выше, в методе - добавляется в сигнатуре метода.
//        public static void main(String[] args) {
//        public static void main(String[] args) throws Exception {

        try {
            voidWithException();
        } catch (Exception e) {
//            e.printStackTrace();
        }

        if (a < 9) {
            throw new UncheckedException("UncheckedException");
        }

        try {
            throw new CheckedException("CheckedException");
        } catch (CheckedException e) {
            e.printStackTrace();
        }


        try {
            voidWithCheckedException();
        } catch (CheckedException e) {
            e.printStackTrace();
            throw new UncheckedException("In catch");
        }


    }

    public static void voidWithException() throws Exception {
        throw new Exception("checked exception");
    }

    public static void voidWithCheckedException() throws CheckedException {
        throw new CheckedException("checked exception");
    }


}

//ассоциация
//агрегация
//композиция
//наследование

//autoboxing
//сколько занимают памяти объекты/массивы и т.п.
