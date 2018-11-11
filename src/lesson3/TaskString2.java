package lesson3;

import java.util.Scanner;

public class TaskString2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите строку: ");
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append(in.nextLine());

        int i = 0, j;
        while (i < strBuilder.length()) {
            j = i + 1;
            while (j < strBuilder.length()) {
                if (strBuilder.charAt(i) == strBuilder.charAt(j) || strBuilder.charAt(j) == ' '){
                    strBuilder.deleteCharAt(j);
                }else{
                    j++;
                }
            }
            i++;
        }
        System.out.println(strBuilder.toString());
    }
}
