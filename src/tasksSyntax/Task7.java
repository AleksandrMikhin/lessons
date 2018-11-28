package tasksSyntax;

//  Написать метод, который определяет, объект какого класса ему передали,
//  и выводит на экран одну из надписей: Кошка, Собака, Птица.


public class Task7 {

    public static String getName(AnimalsTask7 obj) {
        return (obj.getClass().getName().equals("tasksSyntax.CatFromTask7")) ? "Кошка" :
                (obj.getClass().getName().equals("tasksSyntax.DogFromTask7")) ? "Собака" : "Птица";
    }

    public static void main(String[] args) {
        AnimalsTask7 cat = new CatFromTask7();
        AnimalsTask7 dog = new DogFromTask7();
        AnimalsTask7 bird = new BirdFromTask7();

        System.out.println(getName(cat));
        System.out.println(getName(dog));
        System.out.println(getName(bird));
    }

}

class AnimalsTask7 {}

class CatFromTask7 extends AnimalsTask7 {}

class DogFromTask7 extends AnimalsTask7 {}

class BirdFromTask7 extends AnimalsTask7 {}
