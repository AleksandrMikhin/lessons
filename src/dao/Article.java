package dao;

public class Article {
    int id;
    String title;
    int idUser;

    public Article(int id, String title, int idUser) {
        this.id = id;
        this.title = title;
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", idUser=" + idUser +
                '}';
    }
}
