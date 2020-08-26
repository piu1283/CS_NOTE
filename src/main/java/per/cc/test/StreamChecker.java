package per.cc.test;

import per.cc.princeton.tmplate.In;

import java.util.*;

public class StreamChecker {
    public static void main(String[] args) {
        StreamChecker sc = new StreamChecker(new String[]{"cd","f","kl"});
    }

    class TireNode {
        Character val;
        Map<Character, TireNode> children = new HashMap<>();
        boolean show = false;
        boolean end = false;
        public TireNode(Character v){
            this.val = v;
        }
    }
    TireNode root;
    ArrayDeque<TireNode> que;
    Set<Character> set;
    public StreamChecker(String[] words) {
        que = new ArrayDeque<>();
        set = new HashSet<>();
        root = new TireNode(null);
        for(String s : words){
            char[] arr = s.toCharArray();
            TireNode cur = root;
            for(int i = 0; i < arr.length; i++){
                if(cur.children.containsKey(arr[i])){
                    cur = cur.children.get(arr[i]);
                }else{
                    TireNode t = new TireNode(arr[i]);
                    cur.children.put(arr[i], t);
                    cur = t;
                }
            }
            cur.end = true;
        }
        que.addAll(root.children.values());
    }

    public boolean query(char letter) {
        TireNode cur = root;
        int size = que.size();
        boolean flag = false;
        for(int i = 0; i < size; i++){
            TireNode tmp = que.removeFirst();
            if(tmp.val == letter){
                tmp.show = true;
                if(tmp.end = true) {
                    flag = true;
                    set.add(tmp.val);
                }
                que.addAll(tmp.children.values());
            }
        }
        if(set.contains(letter) || flag){
            return true;
        }
        return false;
    }
}

/**
 * Your StreamChecker object will be instantiated and called as such:
 * StreamChecker obj = new StreamChecker(words);
 * boolean param_1 = obj.query(letter);
 */
