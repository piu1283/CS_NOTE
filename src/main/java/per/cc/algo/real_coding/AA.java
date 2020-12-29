package per.cc.algo.real_coding;


import java.util.*;

public class AA {
    public static void main(String[] args) {
        int i = test(new int[]{1, 1, 1, 2}, 0);
        System.out.println(i);
    }

    private static int test(int[] arr, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int value : arr) {
            map.put(value, map.getOrDefault(value, 0) + 1);
        }
        int cnt = 0;
        if (k == 0) {
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if (entry.getValue() >= 2) cnt++;
            }
        } else {
            List<Integer> l = new ArrayList<>(map.keySet());
            for (int i : l) {
                if (map.containsKey(i) && map.containsKey(i + k)) {
                    cnt++;
                    map.remove(i);
                }
            }
        }
        return cnt;
    }
}
