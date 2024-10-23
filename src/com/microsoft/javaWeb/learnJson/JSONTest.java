package com.microsoft.javaWeb.learnJson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONTest {
    @Test
    public void writeJson() throws JsonProcessingException {
        Dog dog = new Dog("小白");
        Person person = new Person("张明", dog);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = objectMapper.writeValueAsString(person);
        System.out.println(jsonStr);
    }

    @Test
    public void readJson() throws JsonProcessingException {
        String personStr = "{\"name\":\"张明\",\"dog\":{\"pname\":\"小白\"}}";
        ObjectMapper objectMapper = new ObjectMapper();
        Person person = objectMapper.readValue(personStr, Person.class);
        System.out.println(personStr);
    }

    @Test
    public void writeMapJson() throws JsonProcessingException {
        Map<String, String> map = new HashMap<>();
        map.put("a", "123");
        map.put("b", "345");
        map.put("c", "678");

        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(map);
        System.out.println(s);
    }

    @Test
    public void writeListArrJson() throws JsonProcessingException {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("b");
        list.add("3");
        ObjectMapper objectMapper1 = new ObjectMapper();
        String s = objectMapper1.writeValueAsString(list);
        System.out.println(s);

        String[] arr = {"1", "2", "3"};
        ObjectMapper objectMapper2 = new ObjectMapper();
        String s1 = objectMapper2.writeValueAsString(arr);
        System.out.println(s1);

    }

}
