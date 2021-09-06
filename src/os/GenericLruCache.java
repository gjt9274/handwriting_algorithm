package os;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class GenericLruCache<K,V> {
    class LinkNode<K,V>{
        K key;
        V value;

        LinkNode<K,V> prev, next;
        public LinkNode(){};
        public LinkNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    int capacity;
    int size;
    Map<K, LinkNode<K,V>> cache = new HashMap<>();
    LinkNode<K,V> head, tail;
    public GenericLruCache(int capacity) {
        this.capacity = capacity;
        head = new LinkNode<>();
        tail = new LinkNode<>();
        head.next = tail;
        tail.prev = head;
    }
    public V get(K key) {
        LinkNode<K,V> node = cache.get(key);
        if (node == null) {
            return null;
        }
        //移到头部
        moveToHead(node);
        return node.value;
    }


    public void put(K k, V v) {
        LinkNode<K,V>  node = cache.get(k);
        if (node == null) {
            //如果不存在就创建一个新的节点
            LinkNode<K,V> newNode = new LinkNode<>(k,v);
            //加入头部
            addToHead(newNode);
            //并添加进cache
            cache.put(k,newNode);
            //然后判断是否超出容量
            if (++size > capacity) {
                LinkNode<K,V> tail = removeTail();//移除尾节点
                cache.remove(tail.key);
                --size;
            }
        }else{
            node.value = v;
            moveToHead(node);
        }
    }
    private void removeNode(LinkNode<K,V> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    private void moveToHead(LinkNode<K,V> node) {
        removeNode(node);
        addToHead(node);
    }

    private LinkNode<K,V> removeTail() {
        LinkNode<K,V> e = tail.prev;
        removeNode(e);
        return e;
    }

    private void addToHead(LinkNode<K,V> node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    public static void main(String[] args) {
        GenericLruCache<Integer, Integer> cache = new GenericLruCache<>(2);
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
