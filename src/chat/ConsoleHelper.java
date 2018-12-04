package chat;

//    ConsoleHelper – класс для чтения или записи в консоль.
//
//    Основные методы:
//        статический метод writeString(String string), который должен выводить строку в консоль
//        статический метод String readString(), который должен считывать строку с консоли.

import java.util.Scanner;

public class ConsoleHelper {

    public static void writeString(String string) {
        System.out.print(string);
    }

    public static String readString() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
