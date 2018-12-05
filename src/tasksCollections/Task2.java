package tasksCollections;

//    Написать программу со следующим функционалом:
//    Считывание 20 чисел с клавиатуры,
//    сохранение их в список,
//    сортировка по трём другим спискам:
//    число нацело делится на 3,
//    нацело делится на 2 и
//    все остальные.
//    Числа, которые делятся на 3 и на 2 одновременно, например 6, попадают в оба списка.

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Task2 {

    public static void main(String[] args) {

        ArrayList<Integer> arrayList1 = new ArrayList();
        ArrayList<Integer> arrayList2 = new ArrayList();
        ArrayList<Integer> arrayList3 = new ArrayList();
        int read;
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 20; i++) {
            read = scanner.nextInt();
            arrayList1.add(read);
            if (read % 2 == 0) arrayList2.add(read);
            if (read % 3 == 0) arrayList3.add(read);
        }
        System.out.println("-----\n" + Arrays.toString(arrayList1.toArray()));
        System.out.println("-----\n" + Arrays.toString(arrayList2.toArray()));
        System.out.println("-----\n" + Arrays.toString(arrayList3.toArray()));

    }
}
