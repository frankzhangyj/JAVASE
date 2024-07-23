package com.microsoft.collectionsFrameWork;

import com.microsoft.unitTestMainFunction.Main;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapDemo {
    /**
     * 拉链法创建hashTable
     * hashMap 容量初始是16 即2^4 且往后大小都是2的幂
     *
     * 1. 计算hash值
     *  (h = key.hashCode()) ^ (h >>> 16);
     *  高16位不动；低16位与高16位做异或运算；高16位的参与，增加了结果的随机性
     *
     * 2. 计算槽位
     * i = (n - 1) & hash]
     *  相当于将计算得到的 哈希值 % (n - 1) 将下标映射到数组中得到槽为
     *
     * 3. 容量永远是2的幂次方
     *  如果是17 那么(n - 1)就是16 二进制与其他数 & 得到只可能是16或者0 其他槽永远无法命中 所以2的幂次方减1后更随机
     *
     * 4. key可以是null
     *  计算的hash值为0 槽位也是0 所以可以存储 key的对象还是null，但到取值的时候，key已经被赋上值，从而导致最终值取不出来
     *
     * 5. 加载因子决定了hashMap底层的table数组的扩容机制
     *  如果元素个数大于 此时容量 * 加载因子 则进行扩容(减少冲突)
     *
     * 6. jdk1.7 中hashMap利用数组 + 链表实现 jdk1.8中利用数组 + 链表 + 红黑树实现
     *  1.8中引入了树化阈值 默认为8 即当链中节点数大于8 则将链表转换为红黑树（前提是数组长度达到树化阈值默认64 否则可以进行扩容）
     *  如果树中节点小于6 则将红黑树转换为链表
     *
     * 7. 树化阈值8 大于 反树化阈值6 不同
     *  防止我们频繁在一个索引位置进行添加和删除元素 从而导致频繁进行树化和反树化
     *
     * 8. 扩容resize()后 原来的index下标处的节点位置
     *  要么就是原来的索引，要么就是原来的索引加上原来数组的长度
     *
     * 9. 采用红黑树原因
     *  因为红黑树可以实现键值对的查找和插入两种操作之间的性能平衡，如果采用严格的avl(平衡二叉树)实现，
     *  每次进行键值对插入或者删除的时候，为了保持这棵树的平衡性，需要对树结构进行旋转的可能性大，导致插入或者删除键值对的效率较低，
     *  但是avl树的查询效率高，而红黑树是和平衡树类似的结构，但是其平衡的要求没有那么严格，因此为了平衡插入和查找的效率，采用红黑树实现。
     *
     * 10. 在多线程操作hashMap时 resize()后可能会形成链表环。
     * @param args
     */
    public static void main(String[] args) {
        // HashMap 底层使用数组 + 单链表 + 红黑树
        HashMap<Integer, String> hashMap = new HashMap<>();
        hashMap.put(1000, "tom");
        hashMap.put(1001, "jack");
        hashMap.put(1002, "shea");

        String value = hashMap.get(1000);
        System.out.println(value);

        hashMap.remove(1001);

        System.out.println(hashMap);
        System.out.println(hashMap.containsKey(1001));
        System.out.println(hashMap.containsValue("shea"));

        // 两者类似
        hashMap.replace(1000, "hello");
        hashMap.put(1000,"nihao");

        // 得到键集合
        Set<Integer> set = hashMap.keySet();
        System.out.println(set);
        // 得到键值对集合
        Set<Map.Entry<Integer, String>> set_1 = hashMap.entrySet();
        System.out.println(set_1);
        // Entry 就是用来管理键值对对象的，将对象包裹起来，提供遍历的方式
        Iterator<Map.Entry<Integer, String>> iterator = set_1.iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> entry = iterator.next();
            if (entry.getKey() == 1000) System.out.println(entry.getValue());
        }

    }
}
