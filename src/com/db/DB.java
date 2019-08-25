package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB
{
    public static Connection getConnection()
    {
        Connection conn = null;
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://127.0.0.1:3306/poker?characterEncoding=utf-8&useSSL=true&serverTimezone=GMT";
        String username = "root";
        String password = "lixiaomin";
        try
        {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        }
        catch(SQLException e)
        {
            System.out.println("找不到驱动程序类 ，加载驱动失败！");
            throw new IllegalStateException(e);
        }
        catch (ClassNotFoundException e)
        {
            throw new IllegalStateException(e);
        }
        return conn;
    }

    public static void close(Connection conn){
        try
        {
            conn.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
