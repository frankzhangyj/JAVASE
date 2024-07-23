package com.microsoft.generics.dao;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DaoTest {
    @Test
    public void test1() {
        CRUD<User> userCRUD = new CRUD<>();
        userCRUD.save("001", new User(1, 10, "frank"));
        userCRUD.save("002", new User(2, 11, "tom"));
        userCRUD.save("003", new User(3, 13, "jane"));
        userCRUD.save("004", new User(4, 14, "jack"));
        List<User> users = new ArrayList<>();
        LinkedList users1 = (LinkedList)users;
        users = userCRUD.list();

        for (User user : users) {
            System.out.println(user);
        }
    }
}
