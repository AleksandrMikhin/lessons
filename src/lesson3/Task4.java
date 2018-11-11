package lesson3;

import java.util.Random;
import java.util.Scanner;

public class Task4 {
    public static void main(String[] args) {
        int n, left = 0, rigth = 0;
        Scanner in = new Scanner(System.in);
        System.out.print("Введите четное положительное число: ");
        while (true) {
            n = in.nextInt();
            if (n>0 && n%2==0){
                break;
            }else{
                System.out.print("введенное число некорректно, ведите четное положительное число: ");
            }
        }
        int[] arr1 = new int[n];
        Random rnd = new Random(System.currentTimeMillis());

        for (int i = 0; i<n; i++) {
            arr1[i] = rnd.nextInt(10) - 5;
            System.out.print(arr1[i] + " ");
            if (i < n / 2) {
                left += Math.abs(arr1[i]);
            } else{
                rigth += Math.abs(arr1[i]);
            }
        }
        System.out.println("\n" + ((left>rigth)?"Сумма модулей левой половины массива больше правой":
                                    (left<rigth)?"Сумма модулей правой половины массива больше левой":
                                            "Суммы модулей половин массивов равны"));
    }
}
