package lesson13;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlConnect {
    static String connectUrl = "jdbc:mysql://localhost:3306/lesson13" +
            "?verifyServerCertificate=false"+
            "&useSSL=false"+
            "&requireSSL=false"+
            "&useLegacyDatetimeCode=false"+
            "&amp"+
            "&serverTimezone=UTC";

    static String user = "root";
    static String pass = "root";

    static String sql = "CREATE TABLE IF NOT EXISTS Example(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "firstColumn TEXT NOT NULL," +
            "secondColumn INTEGER NOT NULL);";


    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        // регистрируем драйвер
        Class.forName("com.mysql.cj.jdbc.Driver");


        try (Connection connection =
                     DriverManager.getConnection(connectUrl, user, pass)){
            Statement statement = connection.createStatement();
//            int row = statement.executeUpdate(sql);
//            System.out.println(row);



        }

    }
}