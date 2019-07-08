/* 16.25 LRU Cache: Design and build a "least recently used" cache, which evicts the least recently used item.
The cache should map from keys to values (allowing you to insert and retrieve a value associated
with a particular key) and be initialized with a max size. When it is full, it should evict the least
recently used item. You can assume the keys are integers and the values are strings. */

/*
1. use a HashMap to store the key and value pairs, a doubly LinkedList to track the least recently used item, an integer for the max size
2. methods: insert new key-value pair, search key and get value, evict the LRU item, update the LRU status whenever insert/retrieve/evict an item
3. Instead of mapping the key to its value, mapping the key to a LinkedList Node so that we can easily find the node when we need to update the LRU status
*/

import java.util.HashMap;

public class LRUCache{
    private int maxSize;
    private HashMap<Integer, LinkedListNode> map =  new HashMap<>();
    private LinkedListNode newest = null;
    private LinkedListNode oldest = null;

    public LRUCache(int size){
        maxSize = size;
    }
    public void add(int key, String val){
        if(map.containsKey(key)){
            removeKey(key);
        }
        LinkedListNode node = new LinkedListNode(key,val);
        if (map.size() == maxSize){
            evictLRU();
        }
        if (newest == null && oldest == null){
            newest = node;
            oldest = node;
        }
        else{
            updateLRU(node);
        }
        map.put(key, newest);
    }

    private void evictLRU(){
        if (oldest == null) {
            return;
        }
        int k = oldest.key;
        map.remove(k);
        oldest = oldest.pre;
        oldest.pos = null;
    }

    public String getValue(int key){
        if (map.containsKey(key)){
            LinkedListNode node = map.get(key);
            updateLRU(node);
            return node.data;
        }
        return null;
    }

    public boolean removeKey(int key){
        if (!map.containsKey(key)){
            return false;
        }
        LinkedListNode node = map.get(key);
        if (node.pre != null) { node.pre.pos = node.pos; }
        if (node.pos != null) { node.pos.pre = node.pre; }
        map.remove(key);
        return true;
    }

    private void updateLRU(LinkedListNode node){
        if (node.pre != null) { node.pre.pos = node.pos; }
        if (node.pos != null) { node.pos.pre = node.pre; }
        node.pos = newest;
        newest.pre = node;
        newest = node;
    }

    private class LinkedListNode{
        public int key;
        public String data;
        public LinkedListNode pre;
        public LinkedListNode pos;
        public LinkedListNode(int k, String s){
            key = k;
            data = s;
        }
    }

    public void printAll(){
        LinkedListNode node = newest;
        while(node != null){
            System.out.println(node.data);
            node = node.pos;
        }
        System.out.println("------------");
    }

    public static void main(String[] args){
        LRUCache c = new LRUCache(3);
        c.add(5, "aaa");
        c.add(4, "bbb");
        c.add(3, "ccc");
        c.printAll();
        c.add(2, "ddd");
        c.printAll();
        c.removeKey(3);
        c.printAll();
        c.evictLRU();
        c.printAll();
        c.add(5, "aaa");
        c.printAll();
        System.out.println(c.getValue(2) + "++++");
        c.printAll();

    }
}