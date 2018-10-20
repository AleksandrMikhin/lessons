import java.util.Scanner;

public class Task4 {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.println("Введите натуральное число: ");

        int number = in.nextInt(), i = 2;
        while (i<number) {
            if (number % i++ == 0) {break;}
        }

        System.out.print("Число " + number + ((i>=number)? " простое" : " составное"));
    }
}
