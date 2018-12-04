package taskCollections;

//    Ввести с клавиатуры 5 слов в список строк.
//    Удалить 3 - ий элемент списка, и вывести оставшиеся элементы в обратном порядке.

import java.util.ArrayList;
import java.util.Scanner;

public class Task4 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ArrayList<String> arrayList = new ArrayList(5);
        for (int i = 0; i < 5; i++) {
            arrayList.add(scanner.nextLine());
        }

        arrayList.remove(2);

        System.out.println("---------------");
        for (int i = arrayList.size()-1; i >=0; i--) {
            System.out.println(arrayList.get(i));
        }

    }

}
