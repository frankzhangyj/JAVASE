package com.microsoft.JDBC;

import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class JDBCTest02 {
    private static Connection connection;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;

    @Test
    public void test() {
        JDBCUtils.init();
        try {
            //1.获取数据库连接和操作对象
            connection = JDBCUtils.getConnection();

            //2.用户输入信息，使用占位符的sql语句，更新一条数据
            String sql = "INSERT INTO info(name, age) values(?, ?)";
            System.out.println("请先输入姓名，再输出年龄，用回车隔开：");
            Scanner scanner = new Scanner(System.in);

            String name = null;
            int age = 0;

            if (scanner.hasNextLine()) {
                name = scanner.nextLine();
            }

            if (scanner.hasNext()) {
                age = scanner.nextInt();
            }

            //3.preparedStatement传入带占位符的sql语句，set方法设置每一个位置的值，并执行更新操作
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            int res = preparedStatement.executeUpdate();

            //4.判断数据是否更新成功
            if (res != 0) {
                System.out.println("Update success");
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                //5.调用重载方法，释放资源
                JDBCUtils.close(connection, preparedStatement);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void deleteTest() {
        try {
            connection = JDBCUtils.getConnection();

            Scanner scanner = new Scanner(System.in);
            int id = 0;

            if (scanner.hasNext()) {
                id = scanner.nextInt();
            }
            
            String sql = "delete from info where id = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            int res1 = preparedStatement.executeUpdate();

            if (res1 != 0) {
                System.out.println("delete success!");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                JDBCUtils.close(connection, preparedStatement);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
