package xml;

import java.util.ArrayList;
import java.util.List;

public class Catalog {
    List<Article> articleList = new ArrayList<>();

    @Override
    public String
    toString() {
        return "Catalog{" +
                "articleList=" + articleList +
                '}';
    }
}
