package lesson25;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.Map;
import java.util.stream.*;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;


public class StreamApi {

    public static void main(String[] args) throws IOException {

        //создание stream
        Integer[] arr = {15, 0, 45, 55, 45, -5};
        //стрим из массива
        Stream<Integer> arrStream = Arrays.stream(arr);

        List<Integer> list = new ArrayList<>(Arrays.asList(arr));
        //стрим из коллекции
        Stream<Integer> listStream = list.stream();

        //метод of
        Stream<String> stringStream = Stream.of("dfdgvfd", "dfgdg", "dgdgfgdf");

        File file = new File("C:\\Users\\User\\Downloads\\wp.txt");
        Stream<String> fileStream = Files.lines(file.toPath());

        //примитивы
        IntStream intStream = IntStream.range(12, 23);
        LongStream longStream = LongStream.builder()
                .add(5)
                .add(77)
                .add(864)
                .build();

        // удалить из списка элементы меньше 20, каждый элемент увеличить на 10
        // вывести в отсортированном порядке
        // Integer[] arr = {15, 0, 45, 55, -5};

        list = new ArrayList<>(Arrays.asList(arr));
        list.stream()
                //удалить из списка элементы меньше 20и
                .filter(s->s>20)
                .map(s->s+10)
                .distinct()  //уникальные
                .sorted()
                //терминальный метод
                .forEach(System.out::println);

        Optional<Integer> optional = Stream.of(1, 2, 3, 6)
                .filter(s->s<5)
                .findFirst();

        Integer i = optional.orElse(0);

        Integer res = Stream.of(1, 2, 3)
                .reduce(0, (k, j)->k+j);
        System.out.println(res);

        Map<String, Long> map =
            // получили стрим из файла
            Files.lines(file.toPath())
            //указываем, что стрим должен быть параллельным
                    .parallel()
           //убираем знаки препинания
                    .map(line->line.toLowerCase().replaceAll("\\pP", " "))
                    .flatMap(line->Arrays.stream(line.split(" ")))
                    //обрезаем пробелы
                    .map(String::trim)
                    .filter(word->!"".equals(word))
                    .collect(groupingBy(identity(), counting()))
            //collect - получтить другую коллекцию из потока
            //groupingBy - разделить коллекцию по условию и вернуть
            //Map<N, List<T>>,
            //где T тип последнего стрима
            //N значение зазделителя
                    .entrySet().parallelStream()
                    .sorted((e1, e2)->e2.getValue().compareTo(e1.getValue()))
                    .limit(10)
                    .collect(toMap(Map.Entry::getKey, Map.Entry::getValue));

        System.out.println(map);

        Stream<String> stringStream1 = Files.lines(file.toPath())
            //указываем, что стрим должен быть параллельным
                .parallel()
            //убираем знаки препинания
                .map(line->line.toLowerCase().replaceAll("\\pP", " "))
                .flatMap(line->Arrays.stream(line.split(" ")))
            //обрезаем пробелы
                .map(String::trim)
                .filter(word->!"".equals(word));


    // количество the
//    Long count = stringStream1
//            .filter("the"::equals)
//            .count();

//    System.out.println(count);

    //выбрать элементы содержащие

        List<String> stringList = stringStream1
                .filter((s)->s.contains("the"))
                .collect(Collectors.toList());

        System.out.println(stringList);

        // Integer[] arr = {15, 0, 45, 55, -5};
        arrStream = Arrays.stream(arr);
        IntSummaryStatistics statistics = arrStream
                .collect(Collectors.summarizingInt(p->p-1));
        System.out.println(statistics);

        //Выбрать пользователей в возрасте от 18 до 40
        //Найти максимальный возраст
        //Найти средний возраст


        User user1 = new User("sddfgd", 56);
        User user2 = new User("sddfgd", 58);
        User user3 = new User("sddfgd", 34);
        User user4 = new User("sddfgd", 14);
        User user5 = new User("sddfgd", 25);

        List<User> listUser = new ArrayList<>();
        listUser.add(user1);
        listUser.add(user2);
        listUser.add(user3);
        listUser.add(user4);
        listUser.add(user5);


        Stream<User> userStream = listUser.stream();
        IntSummaryStatistics statisticsUser = userStream
                .collect(Collectors.summarizingInt((p)->p.age));
        System.out.println(statisticsUser.getMax());
        System.out.println(statisticsUser.getAverage());

//еще вариант
        double average = userStream.mapToInt(User::getAge).average().getAsDouble();

        // Integer[] arr = {15, 0, 45, 55, -5};
        // найти сумму
        list = new ArrayList<>(Arrays.asList(arr));
        Integer integer = list.stream().filter(a->a%2!=0).reduce((j,k)->j+k).orElse(0);
// ??       list.stream().filter(a->a%2!=0).collect(Collectors.summingInt()).intValue();
        System.out.println(integer);

        // проверить, есть ли симкол w во всех словах
         boolean boo = stringStream1.allMatch(s -> s.contains("w"));



    }
}

class User{
    String name;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    int age;
}