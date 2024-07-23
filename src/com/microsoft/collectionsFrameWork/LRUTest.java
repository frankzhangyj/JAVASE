package com.microsoft.collectionsFrameWork;

import com.sun.org.apache.bcel.internal.generic.ATHROW;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUTest {
    public static void main(String[] args) {
        LRU<Integer, Integer> lru = new LRU<>(5, 0.75f);
        lru.put(1, 1);
        lru.put(2, 2);
        lru.put(3, 3);
        lru.put(4, 4);
        lru.put(5, 5);
        lru.put(6, 6);
        lru.put(7, 7);

        System.out.println(lru.get(4));
        lru.put(6, 666);
        // 最久的元素删除
        // 最新的访问4和6移到了后面
        System.out.println(lru);

    }
}

class LRU<K, V> extends LinkedHashMap<K, V> {
    private int capacity;

    public LRU(int capacity, float loadFactor) {
        super(capacity, loadFactor, true);
        this.capacity = capacity;
    }

    // 当元素个数达到缓存容量 就将最近没有使用到的节点删除
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > this.capacity;
    }
}
