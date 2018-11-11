package lesson2;

public class Task5 {
    public static void main(String[] args) {

        int a = 0, b = 1, c;
        System.out.print( b );

        for (int i = 1; i < 11; i++) {
            c = b;
            b = a + b;
            a = c;
            System.out.print(" " + b);
        }
    }
}
