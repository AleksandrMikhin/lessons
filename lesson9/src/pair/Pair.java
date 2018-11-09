package pair;

import classes.Book;

public class Pair<K, V> {  //K, V - любые типы данных, 2 чтобы key и value могли быть разными
    private K key;
    private V value

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public static void main(String[] args) {
//        Pair<int, String> - дженерики не могут работать с примитивными данными - int, float...
        Pair<Integer, String> pair = new Pair<>(12, "значение");
        Integer key = pair.getKey();
        String value = pair.getValue();

        Book tails = new Book("Сказки", 500);

        Pair<String, Book> pair1 = new Pair<>("book1", tails);
        String book = pair1.getKey();
        Book value2 = pair1.getValue();

    }

}