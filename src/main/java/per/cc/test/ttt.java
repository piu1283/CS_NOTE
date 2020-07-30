package per.cc.test;

import java.util.LinkedHashMap;

public class ttt {
    public static void main(String[] args) {
        LinkedHashMap<Integer, String> map = new LinkedHashMap<>(2,0.75f, true);
        map.put(1, "1");
        map.put(3, "2");
        map.put(2, "3");
        map.put(3, "3");
        map.keySet().forEach(System.out::print);
    }
}
