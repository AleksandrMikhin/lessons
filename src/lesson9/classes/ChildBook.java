package lesson9.classes;

public class ChildBook extends Book{
    private String picture;


    public ChildBook(String name, int pages, String picture) {
        super(name, pages);
        this.picture = picture;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "ChildBook{" +
                "picture='" + picture + '\'' +
                ", name='" + name + '\'' +
                ", pages=" + pages +
                '}';
    }
}