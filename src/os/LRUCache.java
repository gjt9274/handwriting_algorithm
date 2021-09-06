package os;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;

public class LRUCache {
    Map<Integer, LinkNode> cache = new HashMap<>();
    int capacity;
    int size;
    LinkNode head, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new LinkNode();
        tail = new LinkNode();
        head.next = tail;
        tail.prev = head;
    }
    public int get(int key) {
        LinkNode node = cache.get(key);
        if (node == null) { //不存在
            return -1;
        }
        //需要更新结点为头节点
        moveToHead(node);
        return node.value;
    }
    public void put(int key, int value){
        LinkNode node = cache.get(key);
        if (node == null) {
            //如果不存在，就新建一个结点
            LinkNode newNode = new LinkNode(key,value);
            //将新结点插入到头部
            addToHead(newNode);
            //并添加进hashmap中
            cache.put(key,newNode);
            ++size;
            if (size > capacity) {//超出容量
                LinkNode tail = removeTail();//移除尾结点
                cache.remove(tail.key);
                --size;
            }
        }else{
            //如果存在，先通过hash表定位，再修改value,然后移动到头部
            node.value = value;
            moveToHead(node);
        }
    }

    private LinkNode removeTail() {
        LinkNode p = tail.prev;
        removeNode(p);
        return p;
    }
    private void removeNode(LinkNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }


    private void addToHead(LinkNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void moveToHead(LinkNode node) {
        removeNode(node);
        addToHead(node);
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1,1);
        cache.put(2,2);
        System.out.println(cache.get(1));
        cache.put(3,3);
        System.out.println(cache.get(2));
        cache.put(4, 4);    // 该操作会使得密钥 1 作废
        System.out.println(cache.get(1));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
    }
}
class LinkNode{
    int key;
    int value;
    LinkNode prev, next;
    public LinkNode(){};
    public LinkNode(int k, int v) {
        key = k;
        value = v;
    }
}