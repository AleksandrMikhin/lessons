package lesson9.storage;

import lesson9.classes.Book;

public class BookContainer<T extends Book> {
    private T book;
    public BookContainer(T book){
        this.book = book;
    }

    public String getBookTitle(){
        return this.book.getName();
    }

    public static void main(String[] args) {
        Book tails = new Book("book123", 500);

        BookContainer<Book> container = new BookContainer<>(tails);
        System.out.println(container.getBookTitle());
    }
}
