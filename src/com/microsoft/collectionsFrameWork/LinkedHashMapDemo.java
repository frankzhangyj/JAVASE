package com.microsoft.collectionsFrameWork;

import java.util.*;

public class LinkedHashMapDemo {
    /**
     *  linkedHashMap继承HashMap 底层利用HashMap(数组 + 链表 + 红黑树) + 双向链表 实现
     *  哈希表中的数据可以按照插入顺序访问 也可以按照访问顺序访问（可以用来实现LRU缓存）
     *  如果按照访问顺序访问 会将这个节点移动到双向链表的末尾
     * @param args
     */
    public static void main(String[] args) {
        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(3, "frank");
        linkedHashMap.put(2, "jack");
        linkedHashMap.put(4, "jane");

        // 按照插入顺序访问
        Set<Map.Entry<Integer, String>> set = linkedHashMap.entrySet();
        Iterator<Map.Entry<Integer, String>> iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> value = iterator.next();
            System.out.println(value.getKey() + ": " + value.getValue());
        }

    }
}
