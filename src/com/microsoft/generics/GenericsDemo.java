package com.microsoft.generics;

import java.util.ArrayList;

public class GenericsDemo {
    public static void main(String[] args) {
        // 通过泛型可以避免集合中存放的是Object类 在使用时需要向下转型
        // ArrayList dogs = new ArrayList()
        ArrayList<Dog> dogs = new ArrayList<>();

        for (Dog dog : dogs) {
            System.out.println(dog.toString());
        }
    }
}

class Dog {
    private String name;
    private int age;

    public Dog() {

    }

}
