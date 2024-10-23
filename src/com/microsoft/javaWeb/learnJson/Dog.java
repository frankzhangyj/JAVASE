package com.microsoft.javaWeb.learnJson;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Dog {
    private String pName;
    public Dog(){}
    public Dog(String pName) {
        this.pName = pName;
    }
}
