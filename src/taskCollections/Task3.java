package taskCollections;

//    Написать программу со следующим функционалом:
//    Считывание  с клавиатуры 10 слов в список строк.
//    Метод doubleValues должен удваивать слова по принципу a,b,c -> a,a,b,b,c,c.
//    Метод printList()  должен выводить результат на экран (каждое значение с новой строки).

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Task3 {

    static String doubleValues(String str) {
        String strResult = null;
        List<String> list = Arrays.asList(str.split(""));
        for (int i = 0; i < list.size(); i++) {
            list.set(i, list.get(i) + list.get(i));
        }
        return String.join("", list);
    }

    static void printList(ArrayList<String> arrayList) {
        for (String elem : arrayList) {
            System.out.println(elem);
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ArrayList<String> arrayList = new ArrayList(10);
        for (int i = 0; i < 10; i++) {
            arrayList.add(doubleValues(scanner.nextLine()));
        }
        System.out.println("---------------");
        printList(arrayList);
    }
}
