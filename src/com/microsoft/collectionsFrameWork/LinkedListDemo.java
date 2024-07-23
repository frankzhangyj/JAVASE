package com.microsoft.collectionsFrameWork;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;

public class LinkedListDemo {
    /**
     * ArraryList 是数组集合 增删慢 查询块
     * LinkedList 是链表集合 增删块 查询慢
     */
    @Test
    public void LinkedListTest() {
        LinkedList<String> linkedList = new LinkedList<String>();
        ArrayList<String> arrayList = new ArrayList<String>();

        arrayList.add("xxx");
        arrayList.add("yyy");

        // 默认采用尾插法 linkLast
        linkedList.add("a");
        linkedList.add("b");
        linkedList.add("c");
        System.out.println(linkedList);

        linkedList.addFirst("d");
        linkedList.addLast("f");
        // 将数组集合尾插入到链表中
        linkedList.addAll(arrayList);
        System.out.println(linkedList);

        // 移除表头表尾采用unlinkFirst unlinkLast
        linkedList.removeFirst();
        linkedList.removeLast();
        // 默认移除表头
        linkedList.remove();
        //
        linkedList.retainAll(arrayList);


        linkedList.getFirst();
        linkedList.getLast();
        // 如果下标小于总数一半 那么就从表头开始向后找 反之
        linkedList.get(3);

        linkedList.clear();
    }
}
