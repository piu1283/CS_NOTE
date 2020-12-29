package per.cc.test;

import java.util.*;

class RandomizedCollection {
    public static void main(String[] args) {
        RandomizedCollection rd = new RandomizedCollection();
        rd.insert(0);
        rd.insert(1);
        rd.remove(0);
        rd.insert(2);
        rd.remove(1);
        rd.getRandom();
    }

    Map<Integer, List<Integer>> map;
    List<Integer> list;
    Random r;

    /** Initialize your data structure here. */
    public RandomizedCollection() {
        r = new Random();
        map = new HashMap<>();
        list = new ArrayList<>();
    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        list.add(val);
        int id = list.size() - 1;
        if(map.containsKey(val)){
            map.get(val).add(id);
            return false;
        }else{
            List<Integer> l = new ArrayList<>();
            l.add(id);
            map.put(val,l);
            return true;
        }
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if(list.isEmpty()) return false;
        int lastId = list.size() - 1;
        int lastVal = list.get(lastId);
        List<Integer> idList = map.get(lastVal);
        if(map.containsKey(val)){
            int removeId = map.get(val).get(map.get(val).size() - 1);
            map.get(val).remove(map.get(val).size() - 1);
            // swap
            int tmp = list.get(lastId);
            list.set(lastId,list.get(removeId));
            list.set(removeId, tmp);
            for(int i = idList.size() - 1; i>=0; i--){
                if(idList.get(i) == lastId){
                    idList.set(i, removeId);
                    break;
                }
            }
            list.remove(lastId);
            if(map.get(val).isEmpty()){
                map.remove(val);
            }
            return true;
        }
        return false;
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        System.out.println(list.size());
        int rNum = r.nextInt(list.size());
        return list.get(rNum);
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */