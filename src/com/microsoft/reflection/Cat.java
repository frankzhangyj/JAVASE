package com.microsoft.reflection;

public class Cat {
    private String name = "cat";
    public int age = 10;

    public Cat() {}

    private Cat(String str, int age) {
        this.name = str;
        this.age = age;
    }
    public Cat(String str) {this.name = str;}

    public void bark() {
        //System.out.println(this.name + " miao...");
    }

    private void sleep(int time) {
        System.out.println("sleep " + time + " h");
    }
}
