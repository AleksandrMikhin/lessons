package tasksSyntax;

//Написать следующую программу:
//        1. Программа должна считывать строки с клавиатуры.
//        и прекращать считывать данные после того как была введена строка "exit".
//        2. Если введенная строка содержит точку(".") и может быть корректно преобразована в число типа Double -
//        должен быть вызван метод print(Double value).
//        3. Если введенная строка может быть корректно преобразована в число типа short и полученное число больше 0,
//        но меньше 128 - должен быть вызван метод print(short value).
//        4. Если введенная строка может быть корректно преобразована в число типа Integer
//        и полученное число меньше или равно 0 или больше или равно 128 - должен быть вызван метод print(Integer value).
//        5. Во всех остальных случаях должен быть вызван метод print(String value).


import java.util.Scanner;

public class Task1 {

    public static void main(String[] args) {

        String str;
        int i, t;

        Scanner in = new Scanner(System.in);
        System.out.print("Введите строки: ");
        do {
            str = in.nextLine();
            if (str.indexOf('.') >= 0)
                try {
                    System.out.println(Double.parseDouble(str));
                }catch(NumberFormatException e){
                    System.out.println("str = " + str);
                    continue;
                }

            try {
                i = Integer.parseInt(str);
                if ((i > 0) && (i < 128))
                    System.out.println( "Short : " + i );

                if ((i <= 0) || (i > 128))
                    System.out.println( "Integer : " + i );

            }catch(NumberFormatException e){
                System.out.println("str = " + str);
                continue;
            }

        }while (!str.equals("exit"));
    }
}
