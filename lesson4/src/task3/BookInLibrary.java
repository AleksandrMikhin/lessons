package task3;

public class BookInLibrary {

    private Book book;
    private int count = 0;

    @Override
    public String toString() {
        return book.name + ": " + count;
    }

    public Book getBook() {
        return book;
    }

    public int getCount() {
        return count;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void addCount(int count) {
        this.count += count;
    }

    public BookInLibrary(Book book, int count) {
        this.book = book;
        this.count = count;
    }

}
