package dao;

import java.sql.*;
import java.util.List;

public interface IDao<T>{

    String urlBase = "jdbc:sqlite:src/lesson28.db";

    void add(T t) throws SQLException;
    void delete(int id) throws SQLException;
    void update(int id, T t) throws SQLException;
    T getById(int id) throws SQLException;
    List<T> getAll() throws SQLException;
}





