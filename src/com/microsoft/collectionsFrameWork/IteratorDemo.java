package com.microsoft.collectionsFrameWork;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class IteratorDemo {
    // for循环适合访问顺序结构,可以根据下标快速获取指定元素.
    // 而Iterator 适合访问链式结构,因为迭代器是通过next()和Pre()来定位的.可以访问没有顺序的集合.
    // 因此ArrayList for i; for each; Iterator 三者都可以使用且时间复杂度接近
    // LinkedList Iterator > for each >> for 数据量大时越明显
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);

        System.out.println("fori");
        // fori适合数据的读取与修改
        for (int i = 0; i < arrayList.size(); i++) {
            arrayList.set(i, 7);
            System.out.println(arrayList.get(i));
        }

        System.out.println("for each");
        // for each适合数据的读取 其本身就是一个小型迭代器 不建议使用对象的引用修改
        // for each绝对不能与.remove()方法一起使用，危险会导致所有数据删除 应使用迭代器的.remove()
        for (Integer e : arrayList) {
            System.out.println(e);
        }

        System.out.println("iterator");
        // Iterator不要使用嵌套，适合数据的读取与修改
        // 迭代器只要拿到这个对象,使用迭代器就可以遍历这个对象的内部。
        // next返回当前的对象 然后自动跳到下一个迭代器
        Iterator<Integer> iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            // 返回对象的引用 因此可以直接修改这个对象
            Object value = iterator.next();
            value = 5;
            if (value.equals(7)) {
                System.out.println(value);
            }
        }

        // itit 迭代iterator
        System.out.println("=========");
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("a", 1);
        hashMap.put("b", 2);
        hashMap.put("c", 3);

        Iterator<Map.Entry<String, Integer>> iterator4 = hashMap.entrySet().iterator();
        while (iterator4.hasNext()) {
            Map.Entry<String, Integer> next = iterator4.next();
            System.out.println(next.getKey());
        }
    }
}
