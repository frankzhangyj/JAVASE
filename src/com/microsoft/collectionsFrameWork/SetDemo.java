package com.microsoft.collectionsFrameWork;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;

public class SetDemo {
    public static void main(String[] args) {
        // HashSet 基于 HashMap 来实现的，是一个不允许有重复元素的集合，允许有 null 值，是无序的，即不会记录插入的顺序
        // 不是线程安全的，如果多个线程尝试同时修改 HashSet，则最终结果是不确定的，必须在多线程访问时显式同步对HashSet 的并发访问
        HashSet<String> hashSet = new HashSet<>();
        hashSet.add("fuck");
        hashSet.add("shit");
        hashSet.add("dick");

        for (String s : hashSet) {
            System.out.println(s);
        }
        System.out.println(hashSet);
        // LinkedList 继承HashSet 可以按照插入顺序输出 即是有序的
        // 其底层是哈希表(LinkedHashMap(HashMap + LinkedList))和链表
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add("asdddf");
        linkedHashSet.add("asdf");
        linkedHashSet.add("jlkjz");
        for (String s : linkedHashSet) {
            System.out.println(s);
        }
        System.out.println(linkedHashSet);
    }
}
