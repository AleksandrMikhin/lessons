package taskCollections;

//    Написать программу со следующим функционалом:
//    Считывание максимум 5 строк с клавиатуры,
//    занесение строк в список,
//    поиск самой короткой строки,
//    вывод самой короткой строки на экран по запросу пользователя (команда в консоле /short string)
//    Если одинаково коротких строк несколько, выводить каждую с новой строки.

import java.util.ArrayList;
import java.util.Scanner;

public class Task1 {

    static ArrayList<String> arrayList = new ArrayList();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < 5; i++) {
            arrayList.add( scanner.nextLine() );
        }

        System.out.print("> ");
        if (scanner.nextLine().equals("short string")) {
            printShortLine();
        }
    }

    private static void printShortLine() {
        int shortLength = getShortLineLength();
        if (shortLength > 0)
            for (String str : arrayList)
                if (str.length() == shortLength)
                    System.out.println(str);
    }

    private static int getShortLineLength() {
        int shortLength = arrayList.get(0).length();
        for (int i = 1; i < arrayList.size(); i++ ){
            if (arrayList.get(i).length() < shortLength)
                shortLength = arrayList.get(i).length();
        }
        return shortLength;
    }
}
