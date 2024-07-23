package com.microsoft.JDBC;

import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTest {
    public static Connection connection;
    public static Statement statement;
    public static ResultSet resultSet;

    @Test
    public void test01() {
        JDBCUtils.init();
        try {
            connection = JDBCUtils.getConnection();
            statement = connection.createStatement();

            String sql01 = "insert into info (name, age) values('jack', 11);";
            String sql02 = "delete from info where name = 'frank';";
            String sql03 = "update info set age = 12 where name = 'jack';";
            String sql04 = "select * from info;";
            // 只有查询操作是executeQuery() 其他操作都是executeUpdate()
            int res01 = statement.executeUpdate(sql01);
            int res02 = statement.executeUpdate(sql02);
            int res03 = statement.executeUpdate(sql03);
            resultSet = statement.executeQuery(sql04);

            if (res01 != 0) {
                System.out.println("insert success");
            }

            if (res02 != 0) {
                System.out.println("delete success");
            }

            if (res03 != 0) {
                System.out.println("updeate success");
            }

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                int age = resultSet.getInt(3);
                System.out.println("[" + id + "," + name + "," + age + "]");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                JDBCUtils.close(connection, statement);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
