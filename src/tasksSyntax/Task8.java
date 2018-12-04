package tasksSyntax;

//    Ввести с клавиатуры два целых числа, которые будут координатами точки, не лежащей на координатных осях OX и OY.
//    Вывести на экран номер координатной четверти, в которой находится данная точка.
//
//    Принадлежность точки с координатами (a,b) к одной из четвертей определяется следующим образом:
//    для первой четверти a>0 и b>0;
//    для второй четверти a<0 и b>0;
//    для третьей четверти a<0 и b<0;
//    для четвертой четверти a>0 и b<0.
//    Пример для чисел 4 6: 1
//    Пример для чисел -6 -6: 3
//
//    Если точка находится в первой координатной четверти, вывести "1".
//    Если точка находится в второй координатной четверти, вывести "2".
//    Если точка находится в третей координатной четверти, вывести "3".
//    Если точка находится в четвёртой координатной четверти, вывести "4".


import java.util.Scanner;

public class Task8 {

    public static int getQuarter(int a, int b) {
        return (a > 0 && b > 0) ? 1 :
                (a < 0 && b > 0) ? 2 :
                 (a < 0 && b < 0) ? 3 : 4;
    }

    public static void main(String[] args) {

        int x, y;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Введите координаты точки: ");
            x = scanner.nextInt();
            y = scanner.nextInt();
            if (x != 0 && y != 0) break;
        }
        System.out.println( "четверть: " + getQuarter(x, y));
    }


}