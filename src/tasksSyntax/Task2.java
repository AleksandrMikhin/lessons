package tasksSyntax;

//Создать класс Cat.
//        У кота должно быть имя (name, String),
//        возраст (age, int),
//        вес (weight, int),
//        сила (strength, int).
//
//        Реализовать метод boolean fight(Cat anotherCat):
//        реализовать механизм драки котов в зависимости от их веса, возраста и силы.
//        Зависимость придумать самостоятельно.
//        Метод должен определять, выиграли ли мы (this) бой или нет,
//        т.е. возвращать true, если выиграли и false - если нет.
//        Должно выполняться условие:
//        если cat1.fight(cat2) = true,
//        то cat2.fight(cat1) = false
//
//        Сам метод fight не должен выводить данные на экран.

class Cat {
    String name;
    int age, weigth, strength;

    public Cat(String name, int age, int weigth, int strength) {
        this.name = name;
        this.age = age;
        this.weigth = weigth;
        this.strength = strength;
    }

    boolean fight(Cat anotherCat) {
        if ((strength * weigth)/age >= (anotherCat.strength * anotherCat.weigth)/anotherCat.age)
            return true;
        else
            return false;
    }
}


public class Task2 {

    public static void main(String[] args) {
        Cat cat1 = new Cat("cat1", 2, 4, 24);
        Cat cat2 = new Cat("cat2", 3, 7, 20);

        System.out.println( cat1.fight(cat2));
        System.out.println( cat2.fight(cat1));

    }

}
