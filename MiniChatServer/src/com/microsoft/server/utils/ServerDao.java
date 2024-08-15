package com.microsoft.server.utils;

import com.microsoft.DAO.JDBCUtils;
import com.microsoft.common.Message;
import com.microsoft.common.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 与数据库进行交互
 */
public class ServerDao {
    public static Connection connection;
    public static Statement statement;
    public static PreparedStatement preparedStatement;
    public static ResultSet resultSet;

    /**
     * 检查数据库中是否存在用户信息
     * @param user 用户
     * @return 判断数据库中是否存在用户user
     */
    public static boolean chekUser(User user) {
        String userId = user.getId();
        String pwd = user.getPwd();

        try {
            connection = JDBCUtils.getConnection();

            String sql = "select count(user_id) from user_info where user_id = ? and user_pwd = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(userId));
            preparedStatement.setString(2, pwd);

            resultSet = preparedStatement.executeQuery();

            int count = 0;
            while (resultSet.next()) {
                count += resultSet.getInt(1);
            }

            return count != 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 离线存储消息
     * @param message 存储的消息
     */
    public static void storeOfflineMessage(Message message) {
        String receiver = message.getReceiver();
        String sender = message.getSender();
        String content = message.getContent();
        String time = message.getSendTime();

        try {
            connection = JDBCUtils.getConnection();

            String sql = "insert into offline_chat (sender_id, receiver_id, content, time) values (?, ?, ?, ?)";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(sender));
            preparedStatement.setInt(2, Integer.parseInt(receiver));
            preparedStatement.setString(3, content);
            preparedStatement.setString(4, time);

            int res = preparedStatement.executeUpdate();

            if (res != 0) {
                System.out.println("(用户 " + receiver + " ) 未上线," + " (用户 " + sender + " ) 发送的消息已离线存储!");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 得到数据库中存储的用户离线消息
     * @param userId 用户id
     * @return 用户接收到的离线消息
     */
    public static List<String> getOfflineMessage(String userId) {
        List<String> res = new ArrayList<>();
        try {
            connection = JDBCUtils.getConnection();

            String sql = "select * from offline_chat where receiver_id = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(userId));

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String sender = String.valueOf(resultSet.getInt(2));
                String receiver = String.valueOf(resultSet.getInt(3));
                String content = String.valueOf(resultSet.getString(4));
                String time = String.valueOf(resultSet.getString(5));
                res.add(sender + "/" + receiver + "/" + content + "/" + time);
            }

            return res;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将加载过的离线消息从数据库中移除
     * @param userId 用户id
     */
    public static void removeOfflineMessage(String userId) {
        try {
            String sql = "delete from offline_chat where receiver_id = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(userId));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
