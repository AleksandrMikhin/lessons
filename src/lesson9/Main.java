package lesson9;

import lesson9.classes.Book;
import lesson9.classes.Car;
import lesson9.classes.ChildBook;
import lesson9.storage.Storage;

public class Main {

    public static void main(String[] args) {
        Book tails = new Book("Сказки", 500);
        Book flowers = new Book("Цветы", 600);

        Car car = new Car("green", 1000);

        Storage<Book> bookStorage = new Storage<>();
        bookStorage.add(tails);
        bookStorage.add(flowers);
//        bookStorage.add(car); будет возникать ошибки

        // ClassCastException возникает на моменте исполнения
//        Book bookFromStorage = (Book) bookStorage.get(2); приведение типов не нужно
        Book bookFromStorage = bookStorage.get(1);
        System.out.println(bookFromStorage);

        Storage<ChildBook> childBookStorage = new Storage<>();
        ChildBook bears = new ChildBook("книга1", 200, "pic");
        ChildBook coloring = new ChildBook("книга2", 200, "pic");

        childBookStorage.add(bears);
        Book firstBook = getFirstBook(childBookStorage);
    }


//    public void someVoid(Storage<Integer> storage);
//    public void someVoid(Storage<String> storage);
//    public void someVoid(Storage storage); - после компиляции они будут равны <> убираются - заранее думать!

//    public static Book getFirstBook(Storage<Book> storage) {
//        return storage.get(0);
//    }

    public static Book getFirstBook(Storage<? extends Book> storage) {  //также и с наследуемыми - ? extends - уточнение
//        storage.add(new Book("book", 20)); //при использовании extends добавление невозможно
        return storage.get(0);
    }

    public static void addToStore(Storage<? super Book> storage){  //используем для этого super
        storage.add(new ChildBook("book", 20, "pic"));

//        Book b = storage.get(0); при этом получить данные из storsge не можем
        Object b = storage.get(0); //только так, но не сохраняются методы
    }

    public static void storage1(Storage<?> storage){  //? - можем добавить только null
        storage.add(null);

//        Book b = storage.get(0); при этом получить данные из storsge не можем
        Object b = storage.get(0); //только так, но не сохраняются методы


    }

//    int / long - обертки Integer/Long

}