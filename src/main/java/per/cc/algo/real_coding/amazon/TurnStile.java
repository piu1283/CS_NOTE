package per.cc.algo.real_coding.amazon;

import java.util.*;

/**
 * https://leetcode.com/discuss/interview-question/798231/Amazon-or-OA-2020-or-Turnstile
 */
public class TurnStile {
    public static void main(String[] args) {
//        int[] calc = calc(5, new int[]{0, 1, 1, 3,3}, new int[]{0, 1, 0, 0, 1});
//        System.out.println(calc);
        ArrayDeque<Integer> que = new ArrayDeque<>();
        Random r = new Random();
        for(int i = 0; i < 10; i++){
            System.out.println(r.nextInt(100));
        }

    }

    private static int[] calc(int numCustoms, int[] arrTime, int[] direction) {
        // int[] {0:id, 1:time}
        PriorityQueue<int[]> enter = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        PriorityQueue<int[]> exit = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        for (int i = 0; i < arrTime.length; i++) {
            if (direction[i] == 1) {
                exit.add(new int[]{i, arrTime[i]});
            }else{
                enter.add(new int[]{i, arrTime[i]});
            }
        }
        int preAction = 1;
        int preTime = 0;
        int[] res = new int[numCustoms];
        while (!enter.isEmpty() || !exit.isEmpty()) {
            if (enter.isEmpty()) {
                int[] ex = exit.poll();
                res[ex[0]] = ex[1];
                continue;
            }
            if (exit.isEmpty()) {
                int[] en = enter.poll();
                res[en[0]] = en[1];
                continue;
            }
            if (enter.peek()[1] > exit.peek()[1]) {
                int[] ex = exit.poll();
                res[ex[0]] = ex[1];
                preTime = ex[1];
                preAction = 1;
            }else if(enter.peek()[1] < exit.peek()[1]){
                int[] en = enter.poll();
                res[en[0]] = en[1];
                preTime = en[1];
                preAction = 0;
            }else{
                int[] en = enter.remove();
                int[] ex = exit.remove();
                if(preTime + 1 != en[1]){
                    preTime = en[1] - 1;
                    preAction = 1;
                }
                if (preAction == 0) {
                    res[en[0]] = en[1];
                    ex[1]++;
                    exit.add(ex);
                }else{
                    res[ex[0]] = ex[1];
                    en[1]++;
                    enter.add(en);
                }
                preTime++;
            }
        }
        return res;
    }
}
