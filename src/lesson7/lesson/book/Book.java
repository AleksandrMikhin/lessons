package lesson7.lesson.book;

import java.util.Objects;

public class Book {
    private String title;
    private String author;
    private int pages;

    final String CONST = "Стр Book"; //final - константы - задаем значение сразу или в конструкторе
    final static String CONST1 = "Стр Booksdfsdf"; //final static - значение сразу или в статическом блоке

    //final метода не дает наследуемым классам не дает переопределить метод класса
    //final класса - не дает от себя наследовать другие классы
    //в объявлении метода аргумент get(final String str) - передаваемый аргумент read-only


    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        // CONST = "авапапаа"; либо здесь
    }


    // класс может быть статическим только вложенный


    public static int soldBook; //static - относятся не к объекту, а к классу.
                                // можно обращаться как через объект, так и через класс.

    static {        // статический блок - одна из реализаций инициализации статических переменных
        soldBook = 5;
    }

    public void sellBook(){
        soldBook++;
    }

    public static String getStatic(){  //в статическом методе недоступны переменные элемента класса
        return "Book sold:" + soldBook;
    }


    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPages() {
        return pages;
    }

    // все классы наследуются от Object
    //   метод toString
    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", pages=" + pages +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return pages == book.pages &&
                Objects.equals(title, book.title) &&
                Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, pages);
    }

    //clone() - возвращает копию объекта

    //getClass() - возвращает ссылку на класс объекта
    //finalize() - для сборщика мусора
    //wait() | notify() | notifyAll() - многопоточность

    //equals() - по умолчанию сравнение объектов с помощью ==
    //          возвращает true|false

    //hashCode() - вычисляет хэш
}
