package tasksCollections;

//    Написать программу, которая должна:
//    Считывать с клавиатуры 2 числа N и M.
//    Ввести N строк и заполнить ими список.
//    Переставить M первых строк в конец списка.

import java.util.ArrayList;
import java.util.Scanner;

public class Task6 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите числа N, M:");
        int n = scanner.nextInt(), m;
        while (true) {
            m = scanner.nextInt();
            if (m < n) break;
            System.out.println("число M должно быть меньше числа N ( " + n + " )");
        }
        System.out.println("Введите " + n + " строк:");
        scanner.nextLine();

        ArrayList<String> arrayList = new ArrayList<>(n-1);
        for (int i = 0; i < n; i++) {
            arrayList.add(scanner.nextLine());
        }
        for (int i = 0; i < m; i++) {
            arrayList.add(arrayList.get(0));
            arrayList.remove(0);
        }

        System.out.println("---------------");
        for (String str: arrayList) {
            System.out.println(str);
        }

    }




}
