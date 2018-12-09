package lesson15.homeTasks;

//    С консоли считать имя файла.
//    Посчитать в файле количество запятых. Вывести информацию в консоль.

import java.io.*;
import java.util.Scanner;

public class Task5 {

    public static void main(String[] args) {

        System.out.print("Введите имя файла: ");
        String string;
        int count = 0;
        try (Scanner fileScanner = new Scanner(new File(new Scanner(System.in).nextLine()))){
            while (fileScanner.hasNext())
                count += fileScanner.nextLine().replaceAll("[^,]", "").length();

            System.out.println("Количество запятых: " + count);

        } catch (FileNotFoundException e) {
            System.out.println("Введенный файл не найден!");
            return;
        }
    }

}
