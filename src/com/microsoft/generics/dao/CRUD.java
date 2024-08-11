package com.microsoft.generics.dao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.*;
@Getter
@Setter
@ToString
public class CRUD<T> {
    private Map<String, T> map = new HashMap<>();

    public void save(String id, T entity) {
        if (map.containsKey(id)) {
            throw new RuntimeException("对象已经存在");
        }

        map.put(id, entity);
    }

    public T get(String id) {
        return map.get(id);
    }

    public void update(String id, T entity) {
        map.put(id, entity);
    }

    public List<T> list() {
        List<T> res = new ArrayList<>();
        Iterator<Map.Entry<String, T>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, T> next = iterator.next();
            res.add(next.getValue());
        }

        return res;
    }

    public void delete(String id) {
        if (!map.containsKey(id)) {
            return;
        }

        map.remove(id);
        return;
    }
}
