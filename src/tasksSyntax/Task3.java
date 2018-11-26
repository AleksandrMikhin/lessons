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

    String name;
    int weigth;
    int age;
    String color;
    String adds;
    private boolean home;
    private static int count = 0;

    public Dog(String name) {
        this.name = name;
        this.color = "Серый";
        this.adds = "Cкрывает";
        this.age = 1;
        this.weigth = 10;
        this.home = true;
        count++;
    }

    public Dog(String name, int age, int weigth) {
        this.name = name;
        this.color = "Серый";
        this.adds = "Скрывает";
        this.age = (age < 1)? 1: age;
        this.weigth = (weigth < 1)? 1: weigth;
        this.home = true;
        count++;
    }

    public Dog(String name, int age) {
        this.name = name;
        this.color = "Серый";
        this.adds = "";
        this.age = (age < 1)? 1: age;
        this.weigth = 10;
        this.home = true;
        count++;
    }

    public Dog(int weigth, String color) {
        this.name = "Неизвесно";
        this.color = color;
        this.adds = "";
        this.age = 2;
        this.weigth = weigth;
        this.home = false;
        count++;
    }

    public Dog(int weigth, String color, String adds) {
        this.name = "Неизвесно";
        this.color = "Серый";
        this.adds = adds;
        this.age = 2;
        this.weigth = weigth;
        this.home = true;
        count++;
    }

    static int getDogCount(){
        return count;
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
        Dog dog2 = new Dog(7, "gray");

        Dog dog3 = new Dog("bobik", 5, 10){
            @Override
            public String toString() {
                return this.getClass().getName() + ":::" + super.toString();
            }
        };

        System.out.println(dog1);
        System.out.println(dog2);
        System.out.println("Count: " + Dog.getDogCount());
        System.out.println(dog2.getClass().getName());
        System.out.println(dog3);

    }

}
