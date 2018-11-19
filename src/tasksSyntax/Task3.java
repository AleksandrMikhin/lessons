package tasksSyntax;

//Создать класс Dog с пятью конструкторами:
//        - Имя,
//        - Имя, вес, возраст
//        - Имя, возраст (вес стандартный)
//        - вес, цвет (имя, адрес и возраст неизвестны, это бездомный пес)
//        - вес, цвет, адрес (домашний пес)
//
//        Задача конструктора – сделать объект валидным.
//        Например, если вес неизвестен, то нужно указать какой-нибудь средний вес.
//        Какие свойства необходимо задать по умолчанию, а какие нет, определить самостоятельно
//
//        В классе реализовать счетчик создаваемых объектов и метод  int getDogCount(), который возвращает количество созданных объектов класса

class Dog {
    String name, color, adds;
    int age, weigth;
    private boolean home;

    public Dog(String name) {
        this.name = name;
        this.color = "Серый";
        this.adds = "скрывает";
        this.age = 1;
        this.weigth = 10;
        this.home = true;
    }

    public Dog(String name, int age, int weigth) {
        this.name = name;
        this.color = "Серый";
        this.adds = "Скрывает";
        this.age = (age < 1)? 1: age;
        this.weigth = (weigth < 1)? 1: weigth;
        this.home = true;
    }

    public Dog(String name, int age) {
        this.name = name;
        this.color = "Серый";
        this.adds = "";
        this.age = (age < 1)? 1: age;
        this.weigth = 10;
        this.home = true;
    }

    public Dog(int weigth, String color) {
        this.name = "бездомный";
        this.color = color;
        this.adds = "";
        this.age = 2;
        this.weigth = weigth;
        this.home = false;
    }

    public Dog(int weigth, String color, String adds) {
        this.name = "неизвесно";
        this.color = "Серый";
        this.adds = adds;
        this.age = 2;
        this.weigth = weigth;
        this.home = true;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name = '" + name + '\'' +
                ", color = '" + color + '\'' +
                ", adds = '" + adds + '\'' +
                ", age = " + age +
                ", weigth = " + weigth +
                ", home = " + (home?"Домашний":"Бездомный") +
                '}';
    }
}


public class Task3 {
    public static void main(String[] args) {
        Dog dog1 = new Dog("bobik", 5, 10);
        System.out.println(dog1);
    }

}
