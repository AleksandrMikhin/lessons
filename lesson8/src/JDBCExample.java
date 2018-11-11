import org.sqlite.JDBC;

import java.sql.*;

public class JDBCExample {
    //создать таблицу

    public static void createTable() throws SQLException {

        String sql = "CREATE TABLE IF NOT EXISTS Example(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "firstColumn TEXT NOT NULL," +
                "secondColumn INTEGER NOT NULL);";
        // sqllite типы - TEXT, REAL, INTEGER, BLOB, NUMERIC


        // регистрируем драйвер
        DriverManager.registerDriver(new JDBC());

        try (Connection connection =
                     DriverManager.getConnection("jdbc:sqlite:src/lesson8.db")){
            Statement statement = connection.createStatement();
            int row = statement.executeUpdate(sql);
            System.out.println(row);
        }
    }


    //вставить данные в таблицу

    public static void insertToTable() throws SQLException {
        String sql = "INSERT INTO Example (firstColumn, secondColumn) VALUES ('Value 1', 123);";

        try (Connection connection =
                     DriverManager.getConnection("jdbc:sqlite:src/lesson8.db")){
            Statement statement = connection.createStatement();
            int row = statement.executeUpdate(sql);
            System.out.println(row);
        }

    }

    // получить данные из таблицы

    public static void selectData() throws SQLException {
        String sql = "SELECT * FROM Example WHERE id > 1";

        try (Connection connection =
                     DriverManager.getConnection("jdbc:sqlite:src/lesson8.db")){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String str = resultSet.getString("firstColumn");
                int i = resultSet.getInt("secondColumn");
                System.out.println(str + " : " + i);
            }
        }

    }


    public static void main(String[] args) {
        try {
//            JDBCExample.createTable();
//            JDBCExample.insertToTable();
            JDBCExample.selectData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
