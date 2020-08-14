package per.cc.test;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class IPO {
    public static void main(String[] args) {
        System.out.println(calc(new int[]{
                1,1,1,1,2,2,2,3,3,3,5,5,5,6,6,6,7,7,7,7,11,11,11,11
        }));
    }

    private static int calc(int [] reqs){
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int cnt = 0;
        int drop = 0;
        map.put(0, 0);
        for (int req : reqs) {
            cnt++;
            map.put(req,cnt);
            boolean flag = true;
            // judge 1
            if(flag) {
                Map.Entry<Integer, Integer> entry = map.floorEntry(req - 1);
                if (cnt - entry.getValue() > 3) {
                    drop++;
                    flag = false;
                }
            }
            // judge 10
            if(flag) {
                int target10 = Math.max(0, req - 10);
                Map.Entry<Integer, Integer> entry10 = map.floorEntry(target10);
                if (cnt - entry10.getValue() > 20) {
                    drop++;
                    flag = false;
                }
            }
            // judge 60
            if(flag) {
                int target60 = Math.max(0, req - 10);
                Map.Entry<Integer, Integer> entry60 = map.floorEntry(target60);
                if (cnt - entry60.getValue() > 60) {
                    drop++;
                }
            }
        }
        return drop;
    }
}
