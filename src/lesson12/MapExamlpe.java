package lesson12;

import java.util.*;

public class MapExamlpe {
    public static void main(String[] args) {
        User user = new User(25, "Имя");
        System.out.println(user.hashCode());


//          Map
//    1. хранение данных в паре ключ-значение
//    2. ключи не дублируются
//    3. хранение не зависит от реализации
//    4. могут хранить любые типы данных
//    использование null в качестве ключа зависит от реализации

//        boolean containsKey(Object key);
//        boolean containsValue(Object val);

//    void putAll(Map<?extends K, ?extends V> m);
    //основные методы
//        get(T key);
//        put(T key, T val);

    //реализации Map
//        HashMap
//        TreeMap
//        EnumMap
//        LinkedHashMap
//        WeakHashMap
//        IdentityHashMap - сравнение ключей с помощью разницы

//      HashMap - если ключ null - добавит на первую позицию
//      хранятся в связанном списке в Map.Entry
//      в порядке возрастания хеш кода ключа
//      если ключ совпадает то идет до следующенго элемента, сравнивая хэш и вставляет за ним.

        Map<Integer, String> hashMap = new HashMap<>();
        hashMap.put(1, "Elem 1");
        hashMap.put(2, "Elem 2");
        hashMap.put(2, "Elem 3");
        hashMap.put(null, "Elem 3");
        System.out.println(hashMap);
        System.out.println(hashMap.get(1));

//        перебрать элементы
        for (Map.Entry entry: hashMap.entrySet()){
            System.out.println("Key: " + entry.getKey() +
                    " Value: " + entry.getValue());
        }


//      добавляет элементы в порядке добавления

        Map<Integer, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(1, "Elem 1");
        linkedHashMap.put(2, "Elem 2");
        linkedHashMap.put(2, "Elem 3");
        System.out.println(linkedHashMap);
        System.out.println(linkedHashMap.get(1));

//        перебрать элементы
        for (Map.Entry entry: hashMap.entrySet()){
            System.out.println("Key: " + entry.getKey() +
                    " Value: " + entry.getValue());
        }


        Map<Integer, String> treeMap = new TreeMap<>(); // не может хранить ключ в виде null
        treeMap.put(2, "Elem 2");
        treeMap.put(1, "Elem 1");
        treeMap.put(3, "Elem 3");
        System.out.println(treeMap);
        System.out.println(treeMap.get(1));

//        бинарное дерево
//        - корень - верхний узел
//        - листья - узлы без потомков
//        Значение левого потомка должно быть меньше родителя
//        Значение правого узла должно быть больше либо равно родителя

//        красно-черное бинарное дерево - на этом основан TreeMap и TreeSet
//        + хранит цвет
//        элементы добавляются черные, а потом перекрашиваются
//        - Корень и листья всегда в черные
//        - Каждый красный узел имеет 2 черных потомка
//        - Все пути от узла (корня) до листьев должны иметь одинаковое количество черных узлов

//        перебрать элементы
        for (Map.Entry entry: treeMap.entrySet()){
            System.out.println("Key: " + entry.getKey() +
                    " Value: " + entry.getValue());
        }



        User user1 = new User(1, "user1");
        User user2 = new User(2, "user2");
        User user3 = new User(3, "user3");

        Map<Integer, User> userTeeMap = new TreeMap<>();
        userTeeMap.put(1, user1);
        userTeeMap.put(2, user2);
        userTeeMap.put(3, user3);
        System.out.println(userTeeMap);

        TreeSet<User> treeSet = new TreeSet<>();
        treeSet.add(user1);  // для этого переопределяем метод compareTo из Comparable
        treeSet.add(user2);
        treeSet.add(user3);
        System.out.println(treeSet);



        Map<Integer, String> hashMap2 = new HashMap<>();
        hashMap.put(1, "Elem 1");
        hashMap.put(2, "Elem 2");
        hashMap.put(2, "Elem 3");
        hashMap.put(null, "Elem 3");
        System.out.println(hashMap);
        System.out.println(hashMap.get(1));

//        перебрать элементы
        for (Map.Entry entry: hashMap.entrySet()){
            System.out.println("Key: " + entry.getKey() +
                    " Value: " + entry.getValue());

        }

     //   hashMap.entrySet().remove();

        System.out.println(hashMap.keySet()); // set ключей (коллекция)
        System.out.println(hashMap.values()); // set значений

    }
}
