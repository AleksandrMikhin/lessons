package dao;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

class UserDao extends AClass<User> implements IDao<User>{

    final static String sqlTable = "CREATE TABLE IF NOT EXISTS User(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "login TEXT NOT NULL);";

    final String insertSQL = "INSERT INTO User(id, login) VALUES (?, ?);";
    final String deleteSql = "DELETE FROM User WHERE id=?;";
    final String updateSql = "UPDATE User SET id=?, login=? WHERE id=?;";
    final String getById = "SELECT * FROM User WHERE id=?;";
    final String getAll = "SELECT * FROM User";


    public UserDao() throws SQLException {
        upConnect();
        sqlExecute(sqlTable);
    }

    @Override
    public void add(User user) throws SQLException {
        sqlExecute(insertSQL, intParam(user.id), strParam(user.login));
    }

    @Override
    public void delete(int id) throws SQLException {
        sqlExecute(deleteSql, intParam(id));
    }

    @Override
    public void update(int id, User user) throws SQLException{
        sqlExecute(updateSql, intParam(id), strParam(user.login), intParam(user.id));
    }

    @Override
    public User getById(int id) throws SQLException {
        ResultSet resultSet = sqlExecuteResult(getById, intParam(id));
        resultSet.next();
        User user = new User(resultSet.getInt("id"), resultSet.getString("login"));
        closeConnect();
        return user;
    }

    @Override
    public List<User> getAll()  throws SQLException {
        ResultSet resultSet = sqlExecuteResult(getAll);

        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            users.add(new User(resultSet.getInt("id"), resultSet.getString("login")));
        }
        closeConnect();
        return users;
    }

}