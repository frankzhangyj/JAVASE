package com.microsoft.javaWeb.learnJson;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Person {
    private String name;
    Dog dog;

    public Person() {

    }

    public Person(String name, Dog dog) {
        this.name = name;
        this.dog = dog;
    }
}
