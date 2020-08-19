package per.cc.test;

import java.util.HashMap;

class LRUCache {
    HashMap<String, Node> map;
    Node first;
    Node last;
    int capacity;
    int len;

    public LRUCache(int n) {
        this.capacity = n;
        this.len = 0;
        this.first = new Node();
        this.last = new Node();
        first.next = last;
        last.pre = first;
        map = new HashMap<>(n);
    }

    private void moveToFront(Node n) {
        Node tmp = first.next;
        first.next = n;
        n.pre = first;
        n.next = tmp;
        tmp.pre = n;
    }

    private void removeNode(Node n) {
        Node pre = n.pre;
        Node nxt = n.next;
        pre.next = nxt;
        nxt.pre = pre;
        n.next = null;
        n.pre = null;
    }

    public String getVal(String key) {
        if (!map.containsKey(key)) {
            return null;
        } else {
            moveToFront(map.get(key));
            return map.get(key).val;
        }
    }

    public void putVal(String key, String val) {
        if (map.containsKey(key)) {
            map.get(key).val = val;
            moveToFront(map.get(key));
        } else {
            if (len == capacity) {
                Node removeNode = last.pre;
                removeNode(removeNode);
                map.remove(removeNode.key);
                len--;
            }
            Node newNode = new Node(key, val);
            map.put(key, newNode);
            moveToFront(newNode);
            len++;
        }

    }
}

class Node {
    String key;
    String val;
    Node next;
    Node pre;

    public Node() {
    }

    public Node(String Key, String val) {
        this.key = Key;
        this.val = val;
    }
}
