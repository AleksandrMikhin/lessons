package task3;

import task2.Node;

public class Library {

    private BookInLibrary[] books;

    public Library(int length) {
        books = new BookInLibrary[length];
    }

    int length(){
        return books.length;
    }

    int findBook(Book book){
        for (int i = 0; i < books.length; i++){
            if (books[i].getBook() == book)
                return i;
        }
        return -1;
    }

    void put(Book book, int quantity){
        int find = findBook(book);
        if (find > -1){
            books[find].addCount(quantity);
        }else{
            for (int i = 0; i < books.length; i++){
                if (books[i].getBook() == null) continue;
                else{
                    books[i].setBook(book);
                    books[i].addCount(quantity);
                    return;
                }
            }
            System.out.println("Больше места нет.");
        }
    }

    int get(Book book, int quantity){
        int find = findBook(book);
        if (find > -1) {
            if (books[find].getCount() >= quantity){
                books[find].addCount(-quantity);
                return books[find].getCount();
            }else
                return books[find].getCount()-quantity;
        }

        return find;
    }

    void listLibrary(){
        for (int i = 0; i < books.length; i++){
            System.out.println(i + ". " + books[i].toString());
        }
    }
}
