package lesson3;

import java.util.Random;

public class Task3 {
    public static void main(String[] args) {
        int[] arr1 = new int[15];
        int a = 0;
        Random rnd = new Random(System.currentTimeMillis());

        for (int i = 0; i<15; i++){
            arr1[i] = rnd.nextInt(9);
            System.out.print(arr1[i] + " ");
            if (arr1[i]%2 == 0) {
                a++;
            }
        }
        System.out.println("\nЧетных элементов: " + a);
    }
}
