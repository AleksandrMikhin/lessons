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

class DogBuilder {

    private String name;
    private int weigth;
    private int age;
    private String color;
    private String adds;
    private boolean home;
    private static int count;


    public static class Builder {
        private String name = "неизвесно";
        private int weigth;
        private int age;
        private String color = "серый";
        private String adds = "неизвестен";
        private boolean home;

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder weigth(int weigth){
            this.weigth = weigth;
            return this;
        }

        public Builder age(int age){
            this.age = age;
            return this;
        }
        public Builder color(String color){
            this.color = color;
            return this;
        }
        public Builder adds(String adds){
            this.adds = adds;
            return this;
        }

        public Builder home(boolean home){
            this.home = home;
            return this;
        }
        public DogBuilder build() {
            return new DogBuilder(this);
        }
    }

    private DogBuilder(Builder builder) {
        this.name = builder.name;
        this.color = builder.color;
        this.adds = builder.adds;
        this.age = (builder.age < 1)? 1: builder.age;
        this.weigth = (builder.weigth < 1)? 1: builder.weigth;
        this.home = builder.home;
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


public class Task3Builder {

    public static void main(String[] args) {
        DogBuilder dog1 = new DogBuilder.Builder().name("scharik").age(10).weigth(7).home(true).build();
        DogBuilder dog2 = new DogBuilder.Builder().name("scharik").adds("Не дом и не улица").home(true).build();

        System.out.println(dog1);
        System.out.println(dog2);
        System.out.println("Count: " + DogBuilder.getDogCount());
    }

}
