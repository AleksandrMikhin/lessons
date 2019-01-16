package dao;

import org.sqlite.JDBC;

import java.sql.*;

abstract class AClass<T> implements IDao<T>{

    protected Connection connection;

    protected void upConnect() throws SQLException {
        DriverManager.registerDriver(new JDBC());
        connection = DriverManager.getConnection(urlBase);
    }

    protected void closeConnect() throws SQLException {
        connection.close();
    }
    protected PreparedStatement getPrepare(String sqlString) throws SQLException{
        if (connection.isClosed()) upConnect();
        return connection.prepareStatement(sqlString);
    }

    protected String strParam(String param){
        return '\'' + param + '\'';
    }

    protected String intParam(int param){
        return String.valueOf(param);
    }

    protected void sqlExecute(String sqlString, String ... param) throws SQLException {
        if (connection.isClosed()) upConnect();
        for (int i = 0; i < param.length; i++)
            sqlString = sqlString.replaceFirst("\\?", param[i]);
        getPrepare(sqlString).executeUpdate();
        closeConnect();
    }

    protected ResultSet sqlExecuteResult(String sqlString, String ... param) throws SQLException {
        ResultSet res;
        if (connection.isClosed()) upConnect();
        for (int i = 0; i < param.length; i++)
            sqlString = sqlString.replaceFirst("\\?", param[i] );
        res = getPrepare(sqlString).executeQuery();
        return res;
    }
}
