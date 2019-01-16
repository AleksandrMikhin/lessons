package dao;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

class ArticleDao extends AClass<Article> implements IDao<Article>{

    final static String sqlTable = "CREATE TABLE IF NOT EXISTS Article(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "title TEXT NOT NULL," +
            "id_user INTEGER NOT NULL," +
            "FOREIGN KEY (id_user) REFERENCES User(id));";

    final String insertSQL = "INSERT INTO Article(id, title, id_user) VALUES (?, ?, ?);";
    final String deleteSql = "DELETE FROM Article WHERE id=?;";
    final String updateSql = "UPDATE Article SET id=?, title=?, id_user WHERE id=?;";
    final String getById = "SELECT * FROM Article WHERE id=?;";
    final String getAll = "SELECT * FROM Article";


    public ArticleDao() throws SQLException {
        upConnect();
        sqlExecute(sqlTable);
    }

    @Override
    public void add(Article article) throws SQLException {
        sqlExecute(insertSQL, intParam(article.id), strParam(article.title), intParam(article.idUser));
    }

    @Override
    public void delete(int id) throws SQLException {
        sqlExecute(deleteSql, String.valueOf(id));
    }

    @Override
    public void update(int id, Article article) throws SQLException{
        sqlExecute(updateSql, intParam(article.id), strParam(article.title), intParam(article.idUser), intParam(article.id));
    }

    @Override
    public Article getById(int id) throws SQLException {
        ResultSet resultSet = sqlExecuteResult(getById, intParam(id));
        resultSet.next();
        Article article = new Article(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getInt("id_user"));
        closeConnect();
        return article;
    }

    @Override
    public List<Article> getAll()  throws SQLException {
        ResultSet resultSet = sqlExecuteResult(getAll);

        List<Article> articles = new ArrayList<>();
        while (resultSet.next()) {
            articles.add(new Article(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getInt("id_user")));
        }
        closeConnect();
        return articles;
    }

}
