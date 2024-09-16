package com.microsoft;

import java.util.HashMap;

/**
 * least Recently Used 最近未使用算法
 * 由一个 双向链表 + 哈希表 构成
 * 双向链表按照访问顺序存储每个节点值
 * 哈希表存储所有节点
 * @param <K> key
 * @param <V> value
 */
public class LRUCache<K, V> {
    /**
     * 双向链表节点
     * @param <K> key
     * @param <V> value
     */
    class Node<K, V> {
        K key;
        V value;
        Node<K, V> pre;
        Node<K, V> nxt;

        Node() {

        }

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private int capacity;
    private HashMap<K, Node> hashMap;
    private Node<K, V> head;
    private Node<K, V> tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.hashMap = new HashMap<>();
        this.head = new Node<>();
        this.tail = new Node<>();
        head.nxt = tail;
        tail.pre = head;
    }

    /**
     * 先在双向链表中删除节点 然后将节点移到表头
     * @param node 节点
     */
    private void moveNodeToHead(Node<K, V> node) {
        removeNode(node);
        addToHead(node);
    }

    /**
     * 移到表头具体操作
     * @param node 节点
     */
    private void addToHead(Node<K, V> node) {
        node.pre = head;
        node.nxt = head.nxt;
        head.nxt.pre = node;
        head.nxt = node;
    }

    /**
     * 删除表尾节点
     */
    private void removeTailNode() {
        removeNode(tail.pre);
    }

    /**
     * 具体删除操作
     * @param node 节点
     */
   private void removeNode(Node<K, V> node) {
        node.pre.nxt = node.nxt;
        node.nxt.pre = node.pre;
   }

    /**
     * 查找具体节点
     * @param key key
     * @return node
     */
   public Node<K, V> get(K key) {
        Node<K, V> reNode = hashMap.get(key);
        // 如果没有查到直接返回
        if (reNode == null) {
            return null;
        }
        // 将该节点移到表头
        moveNodeToHead(reNode);
        return reNode;
   }

    /**
     * 插入节点
     * @param key key
     * @param value value
     */
   public void insert(K key, V value) {
        Node<K, V> node = hashMap.get(key);
        // 如果原来没有插入过这个节点
        if (node == null) {
            // 如果当前hashMap中的节点数大于给定cache值 那么就删除尾节点
            if (hashMap.size() >= capacity) {
                hashMap.remove(key);
                removeTailNode();
            }
            // 插入这个新节点 并且将其移到表头
            Node<K, V> newNode = new Node<>(key, value);
            moveNodeToHead(newNode);
            hashMap.put(key, newNode);
        } else {
            // 如果原来有 那么就更新这个节点 并且将其移到表头
            node.value = value;
            moveNodeToHead(node);
       }
   }
}
