package xml;

public class Article {
    String id;
    String author;
    String title;
    String genre;
    String publishDate;
    String text;

    @Override
    public String toString() {
        return "Article{" +
                "id='" + id + '\'' +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", publishDate='" + publishDate + '\'' +
                ", text='" + text + '\'' +
                '}';
    }

}
