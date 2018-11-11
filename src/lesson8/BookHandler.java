package lesson8;

import org.sqlite.JDBC;

import java.sql.*;
import java.util.Arrays;

public class BookHandler {

    public static void createTable() throws SQLException {

        String sql = "CREATE TABLE IF NOT EXISTS Example(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "title TEXT NOT NULL," +
                "pages INTEGER NOT NULL);";
        // sqllite типы - TEXT, REAL, INTEGER, BLOB, NUMERIC


        // регистрируем драйвер
        DriverManager.registerDriver(new JDBC());

        try (Connection connection =
                     DriverManager.getConnection("jdbc:sqlite:src/books.db")){
            Statement statement = connection.createStatement();
            int row = statement.executeUpdate(sql);
            System.out.println(row);
        }
    }

    public static void insertToTable(Book book) throws SQLException {
        String sql = "INSERT INTO Example (firstColumn, secondColumn) VALUES (?, ?);";

        try (Connection connection =
                     DriverManager.getConnection("jdbc:sqlite:src/lesson8.db")){
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, book.getTitle());
            statement.setInt(2, book.getPages());

            int row = statement.executeUpdate();
            System.out.println(row);
        }
    }


    public static Book selectDataOne(int id) throws SQLException {
        String sql = "SELECT * FROM Example WHERE id = ?";

        try (Connection connection =
                     DriverManager.getConnection("jdbc:sqlite:src/lesson8.db")){
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            Book book = new Book();
            while (resultSet.next()) {
                book.setTitle(resultSet.getString("firstColumn"));
                book.setPages(resultSet.getInt("secondColumn"));
                System.out.println(book.toString());
            }
            return book;
        }

    }


    public static Book[] selectData() throws SQLException {
        String sql = "SELECT * FROM Example";

        try (Connection connection =
                     DriverManager.getConnection("jdbc:sqlite:src/lesson8.db")){
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();
            Book[] books = new Book[10];
            for (int i = 0; resultSet.next(); i++) {
                books[i] = new Book();
                books[i].setTitle(resultSet.getString("firstColumn"));
                books[i].setPages(resultSet.getInt("secondColumn"));
                System.out.println(books[i].toString());
            }
            return books;
        }

    }



    public static void main(String[] args) throws SQLException {
//        try {
//            BookHandler.createTable();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        Book book1 = new Book("Книга1", 150);
        Book book2 = new Book("Книга2", 150);
        Book book3 = new Book("Книга3", 150);

        Book[] books = {book1, book2, book3};


        try {
//            BookHandler.insertToTable(book1);

            BookHandler.selectDataOne(1);
            System.out.println(Arrays.toString(BookHandler.selectData()));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
