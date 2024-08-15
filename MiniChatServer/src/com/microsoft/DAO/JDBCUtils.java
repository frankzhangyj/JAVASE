package com.microsoft.DAO;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

/**
 * 数据库初始化与关闭连接
 */
public class JDBCUtils {
    private static String url;
    private static String user;
    private static String password;
    private static String driver;

    //静态代码，做预处理 将数据库信息从配置文件中读取
    static {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("src//com//microsoft//DAO//database.properties"));

            //读取配置项
            url = properties.getProperty("url");
            user = properties.getProperty("user");
            password = properties.getProperty("password");
            driver = properties.getProperty("driver");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //4.测试是否加载成功
    public static void init() {
        System.out.println("加载成功");
    }

    //5.创建单例，获取配置项
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    //6.释放，关闭结果，关闭仓库，关闭数据库连接
    public static void close(Connection connection, Statement statement, ResultSet resultSet) throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

    public static void close(Connection connection, Statement statement) throws SQLException {
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

}
