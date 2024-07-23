package com.microsoft.JDBC;

import java.sql.*;

public class JDBCDemo01 {
    // 创建一个数据库对象的四个素材
    // 访问目标数据库地址 固定格式
    public static final String URL = "jdbc:mysql://127.0.0.1:3306/student?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT%2B8";
    // 登录名
    public static final String USER = "root";
    // 登录密码
    public static final String PASSWORD = "123456";
    // 注意对于mysql-connector-java 8版本反射加载驱动应该是 com.mysql.cj.jdbc.Driver
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static Connection connection;
    public static Statement statement;
    public static ResultSet resultSet;

    public static void main(String[] args) throws Exception {
        try {
            // 通过反射 运行时动态加载驱动程序
            Class.forName(DRIVER);
            // 获得数据库连接
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            // 获取数据库操作对象
            statement = connection.createStatement();
            // 进行查询
            resultSet = statement.executeQuery("select * from info");
            System.out.println("asdfasdf");
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                int age = resultSet.getInt(3);
                System.out.println("[" + id + "," + name + "," + age + "]");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 数据库操作完成 关闭操作对象 再关闭连接
                resultSet.close();
                statement.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
