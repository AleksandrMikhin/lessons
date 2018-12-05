package taskCollections;

//    Написать программу, которая должна:
//    Считывать с клавиатуры 10 слов в список строк.
//    Определять, является ли список упорядоченным по возрастанию длины строки.
//    В случае отрицательного ответа выводить на экран номер первого элемента, нарушающего такую упорядоченность.


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Task5 {

    public static Boolean sorted(ArrayList<String> arrayList) {

        for (int i = 0; i < arrayList.size()-1; i++) {
            if (arrayList.get(i).length() > arrayList.get(i+1).length()) {
                return false;
            };
        }
        return true;
    }

    public static void sortLength(ArrayList<String> arrayList) {
        Collections.sort(arrayList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                // 1 - больше, -1 - меньше, 0 - равны
                return o1.length() > o2.length() ? 1 : (o1.length() < o2.length()) ? -1 : 0;
            }
        });
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<String> arrayList = new ArrayList(5);
        for (int i = 0; i < 10; i++) {
            arrayList.add(scanner.nextLine());
        }

        if (sorted(arrayList)) {
            System.out.println("Список отсортирован.");
        } else {

            ArrayList<String> arrayListSort = (ArrayList) arrayList.clone();
            sortLength(arrayListSort);

            for (int i = 0; i < arrayList.size(); i++) {
                if (arrayList.get(i).length() != arrayListSort.get(i).length()) {
                    System.out.println("Номер первого в списке не по порядку - " + (i + 1));
                    break;
                }
            }
        }
    }

}
