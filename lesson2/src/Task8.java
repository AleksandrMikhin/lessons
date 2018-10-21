import java.util.Scanner;

public class Task8 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите координаты треугольника \nX1, Y1: ");
        int x1 = in.nextInt(), y1 = in.nextInt();

        System.out.print("X2, Y2: ");
        int x2 = in.nextInt(), y2 = in.nextInt();

        System.out.print("X3, Y3: ");
        int x3 = in.nextInt(), y3 = in.nextInt();

        System.out.println( "Треугольник - " +
                ((((y1-y2)*(y2-y3) + (x2-x1)*(x3-x2)) == 0)
                    || ((y2-y3)*(y3-y1)+(x3-x2)*(x1-x3) == 0)
                    || ((y1-y2)*(y3-y1)+(x2-x1)*(x1-x3) == 0) ? "прямоульный" : "не является прямоугольным"));
    }
}
