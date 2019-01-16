package dao;

import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        try {
            UserDao userDao = new UserDao();
            userDao.add(new User(1,"userOne"));
            userDao.add(new User(2,"userTwo"));

            ArticleDao articleDao = new ArticleDao();
            articleDao.add(new Article(1, "messageOne", 1));
            articleDao.add(new Article(2, "messageTwe", 2));
            articleDao.add(new Article(3, "messageThree", 1));

            System.out.println(userDao.getById(2));
            System.out.println("----------------");
            List<User> users = userDao.getAll();
            for (User user: users){
                System.out.println(user);
            }

            System.out.println("----------------");
            List<Article> articles = articleDao.getAll();
            for (Article article: articles){
                System.out.println(article);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
