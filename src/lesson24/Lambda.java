package lesson24;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Lambda {

    public static int someValue = 12;

    public static boolean tstVoid(Predicate<Integer> predicate, int i){
        return predicate.test(i);
    }

    public static void main(String[] args) {
        //синтаксис
//        (аргументы)->тело;
//        ()->тело;
//        ()->{
//            если тело;
//            на несколько строк;
//            [return возвращаемое значение;] - если без, то просто выполняет
//        }

//        (a, b)->a+b;
//        (a, b)->{
//            ...
//            ...
//            return a + b};

//    Лямда сама по себе не выполняется, только реализации методов интерфейса
//
//        интерфейс маркер - без методов
//        функциональный интерфейс - 1 абстрактный метод



        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello");
            }
        });

        new Thread(()-> System.out.println("hello"));

        Operation plus = (a, b)->a+b;
        Operation minus = (a, b)->a-b;

        double result = plus.execute(12, 23);
        result = minus.execute(24, 56);

        int someLocalValue = 12;


        // не должны менять окружающую среду
        Operation operation = (a, b)->{  // можно не указывать типы данных
            someValue -=a;
//            someLocalValue +=b; только к переменным класса, к локальным нет!
            return someValue;
        };



        //  -- Предикаты --
        //  все в пакете function

        Predicate<Integer> pos = (num)->num>0;
        boolean pridicateRes = pos.test(10);
        Predicate<Integer> neg = (num)->num<0;
        pridicateRes = pos.test(10);

//        можно передать лямду как аргумент. см. метод выше
        System.out.println( tstVoid((num)->num>0, 25) );

        pridicateRes = pos.or(neg).test(5);   // default методы вызывать лямдами нельзя, они только для таких проверок
        System.out.println(pridicateRes);


        //функции
        //     <притимает, возвращает>
        Function<Integer, Integer> plusTen = (a)->a+10;
        Function<Integer, Integer> minusTwo = (a)->a-2;

        int functionRes = plusTen.apply(25);
        functionRes = minusTwo.
                andThen(plusTen).
                compose(minusTwo).
                apply(1);


        ButtonFactory<Button> factory = Button::new;   // :: - нотация - ссылка на метод
        Button button = factory.set("Cancel", "pink");   // либо статическая ссылка Button на метод new
        System.out.println(button);


//        -- Consumer -- что-то делает, но ничего не возвращает
        Consumer<Button> makeResetButton = (button123) -> button123.setValue("Reset");  //один аргумент можно без скобок button123
        makeResetButton.accept(button);
        System.out.println(button);


//        -- Comparator  -
        Button button1 = new Button("First", "gray");
        Button button2 = new Button("Second", "gray");

        Comparator<Button> buttonComparator = Comparator.comparing(Button::getValue);
//                                       (b1, b2)->b1.getValue().compareTo(b2.getValue());
        int compareRes = buttonComparator.compare(button1, button2);
        System.out.println(compareRes);
        int compareReverseRes = buttonComparator.compare(button1, button2);
        System.out.println(compareReverseRes);


//      -- forEach --

        List<Integer> integers = new ArrayList<>(Arrays.asList(10,15,22,44));
        integers.forEach((s)-> System.out.println(s + " "));

        List<Integer> integers1 = new ArrayList<>();
        integers.forEach((s)-> integers1.add(s + 10));

//      -- removeIf() --
        integers.removeIf((s)->s < 20);
        System.out.println(integers);


//      -- Map.forEach() --
        Map<String, String> books = new HashMap<>();
        books.put("book1", "author1");
        books.put("book2", "author2");

        books.forEach((key, value)-> System.out.println("Title: " + key + " Author: " + value));

//      -- Map.compute() --
        books.compute("book1", (key, val)->val+ "add author2");
        System.out.println(books);

//        books.computeIfAbsent();
//        books.computeIfPresent()

//      -- Map.getOrDefaulf() --
        String author = books.getOrDefault("book3", "no author");
        System.out.println(author);



    }

}

@FunctionalInterface
interface Operation{
    double execute (int a, int b);
}

interface ButtonFactory<T extends Button>{
    T set(String value, String color);
}

class Button{
    private String value;
    private String color;

    @Override
    public String toString() {
        return "Button{" +
                "value='" + value + '\'' +
                ", color='" + color + '\'' +
                '}';
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public Button(String value, String color) {
        this.value = value;
        this.color = color;
    }
}
