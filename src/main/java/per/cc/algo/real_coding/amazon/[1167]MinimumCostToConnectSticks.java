package per.cc.algo.real_coding.amazon;

import java.util.PriorityQueue;

/**
 * You have some sticks with positive integer lengths.
 * <p>
 * You can connect any two sticks of lengths X and Y into one stick by paying a cost of X + Y.  You perform this
 * action until there is one stick remaining.
 * <p>
 * Return the minimum cost of connecting all the given sticks into one stick in this way.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: sticks = [2,4,3]
 * Output: 14
 * Example 2:
 * <p>
 * Input: sticks = [1,8,3,5]
 * Output: 30
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= sticks.length <= 10^4
 * 1 <= sticks[i] <= 10^4
 */
class MinimumCostToConnectSticks {
    public int connectSticks(int[] sticks) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i : sticks){
            pq.add(i);
        }
        int res = 0;
        while(pq.size() > 1){
            int one  = pq.poll();
            int two = pq.poll();
            res += one + two;
            pq.add(one + two);
        }
        return res;
    }
}
