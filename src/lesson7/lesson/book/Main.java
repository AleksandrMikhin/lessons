package lesson7.lesson.book;

public class Main {
    public static void main(String[] args) {
        Book book1 = new Book("Война и мир", "Толстой");
        book1.setPages(1000);

        Book book2 = new Book("Война и мир", "Толстой");
        book2.setPages(1000);

        System.out.println(book1.toString());
        System.out.println(book2.toString());

        System.out.println(book1 == book2);     //false
        System.out.println(book1.equals(book2));  //false  //true после переопределения

        System.out.println(Book.getStatic()); //к статике через класс

        book1.sellBook();
        book1.sellBook();

        System.out.println(Book.getStatic()); //к статике через класс

        book2.sellBook();
        System.out.println(Book.getStatic()); //к статике через класс



    }
}
