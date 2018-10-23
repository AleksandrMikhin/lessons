import java.util.Arrays;

public class Lesson3 {
    public static void main(String[] args) {
        //arrays

        int[] arr;
        int arr1[]; //можно и так

        arr = new int[10]; //выделяем память под него

        int a = 90;
        int[] arr3 = {2, 12, 34, 56, 77, a};

        System.out.println(Arrays.toString(arr));
        System.out.println(arr.length);

        int[] arr4 = {2, 3, 4};
        int[] arr5 = arr4; // добавили ссылку на arr4

        int[] arr6 = arr4.clone(); // копирует в новый массив

        int[] arr7 = new int[10];
        System.arraycopy( arr3, 1, arr7, 3, 5); // хотим скопировать начиная с 2го, вставляя с 3го - 5 элементов

        int[] copyArr4 = Arrays.copyOf(arr4, 3); // если новая длина больше - новые заполнит нулями

        int[] copyRangeArr4 = Arrays.copyOfRange(arr4, 2, 5); // скопирует с 3го до!! 6го!

        arr4 = new int[]{2, 4, 66, 77, 32}; // переписать массив разом только пересоздать

        // можно использовать цикл for по-другому
        for (int nn: arr4){
            System.out.println( nn ); // в nn передается элемент массива, все по очереди

        }

        // сравнение массивов
        System.out.println( Arrays.equals(arr4, arr6));

        Arrays.fill(arr7, 233); // заполняет массив числом 233

        Arrays.sort(arr7); // сортирует весь
        Arrays.sort(arr7, 1, 4); // сортирует с 1 по 7 индекс

        Arrays.binarySearch(arr7, 3); // поиск в массиве - если найдет - вернет индекс, если нет, то - отрицательное число

        String str = "hello!";
        String str2 = new String("hello!!!");
        //"Hello332442".length();

        String str3 = String.format("Привет %02d !!!", 8); // создание форматированием (форм. тратит много ресурсов)


        String str9 = "hello!";
        String str4 = new String("hello!");
        String str5 = "hello!";

//        str3 == str5 (одинаковые, т.к. сравниваются объекты "hello!")
//        str3 != str4 (false, т.к. объекты создавались разные)
        str3.equals( str4 ); //true - сравнивает не объекты а посимвольно
        str3.equalsIgnoreCase( str4 ); // сравнивает без учета регистра

        str3.startsWith("he");
        str3.endsWith("!");

        String str7 = "hello!!!";
        String str8 = new String("hello!");

        str7.compareTo(str8); //0-если равны, см. с Ctrl!!

        String a1 = "Astring";
        String b = "Bstring";
        String c = "Cstring";

        String[] strArr = {c, a1, b};
        Arrays.sort(strArr);
        System.out.println(Arrays.toString(strArr));

        System.out.println( a1.substring(5) );
        System.out.println( a1.substring(3,5) );

        System.out.println( a1.contains("str") ); //true если подстрока найдена

        String[] arr444 = {"qwe", "asd", "zxc"};

//        если складывать много строк используем StringBuffer и (StringBuilder - производительный, но не работает в потоках)
//                не знак + (он создает новые объекты, потребляя ресурсы)
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append(str2);
        System.out.println( strBuilder.toString() );


    }
}
