package patterns.singleton;

//Одиночка

public class Singleton1 {

    private static Singleton1 instance;

    //запрет создания объектов вне класса
    private Singleton1() {
    }

    //создание объекта по тредованию
    public static Singleton1 getInstance() {
        if (instance == null) {
            instance = new Singleton1();
        }
        return instance;
    }
}
