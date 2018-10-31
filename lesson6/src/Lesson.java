import lessonBattleunit.*;
import calculator.*;

import java.util.Scanner;

public class Lesson {
    public static void main(String[] args) {

        int a = 9;
        int b = 0;
        Action plus = new Plus(a, b);
        plus.work();
        plus = new Delenie(a, b);
        plus.work();


        Knight knight = new Knight(100, 20);
        Spearman spearman = new Spearman(60, 70);

        Doctor doctor = new Doctor(50, 10);
        doctor.attack(knight);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите название персонажа");
        String userChoice = scanner.nextLine();

        BattleUnitFactory unitFactory = new BattleUnitFactory();
        BattleUnit unit = unitFactory.create(userChoice);
//        if (userChoice.equals("knight")) {
//            unit = new Knight(100, 20);
//        } else if (userChoice.equals("spman")) {
//            unit = new Spearman(70, 40);
//        } else {
//            unit = new Doctor(50, 10);
//        }

        Fight fight = new Fight(unit, spearman);
        fight.fight();
        System.out.println(fight.fightResult());

        // Парадигмы ООП:
        // Абстрацкия
        // Инкапсуляция
        // Полиморфизм
        // Наследование
        //
        // Посылка сообщений
        // Повторное использование


    }
}