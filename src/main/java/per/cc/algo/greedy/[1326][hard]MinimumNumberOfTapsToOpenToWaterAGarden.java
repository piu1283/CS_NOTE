package per.cc.algo.greedy;

import java.util.Arrays;

/**
 * Minimum Number of Taps to Open to Water a Garden
 * There is a one-dimensional garden on the x-axis. The garden starts at the point 0 and ends at the point n. (i.e
 * The length of the garden is n).
 * <p>
 * There are n + 1 taps located at points [0, 1, ..., n] in the garden.
 * <p>
 * Given an integer n and an integer array ranges of length n + 1 where ranges[i] (0-indexed) means the i-th tap can
 * water the area [i - ranges[i], i + ranges[i]] if it was open.
 * <p>
 * Return the minimum number of taps that should be open to water the whole garden, If the garden cannot be watered
 * return -1.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 5, ranges = [3,4,1,1,0,0]
 * Output: 1
 * Explanation: The tap at point 0 can cover the interval [-3,3]
 * The tap at point 1 can cover the interval [-3,5]
 * The tap at point 2 can cover the interval [1,3]
 * The tap at point 3 can cover the interval [2,4]
 * The tap at point 4 can cover the interval [4,4]
 * The tap at point 5 can cover the interval [5,5]
 * Opening Only the second tap will water the whole garden [0,5]
 * <p>
 * Example 2:
 * <p>
 * Input: n = 3, ranges = [0,0,0,0]
 * Output: -1
 * Explanation: Even if you activate all the four taps you cannot water the whole garden.
 * <p>
 * Example 3:
 * <p>
 * Input: n = 7, ranges = [1,2,1,0,2,1,0,1]
 * Output: 3
 * <p>
 * Example 4:
 * <p>
 * Input: n = 8, ranges = [4,0,0,0,0,0,0,0,4]
 * Output: 2
 * <p>
 * Example 5:
 * <p>
 * Input: n = 8, ranges = [4,0,0,0,4,0,0,0,4]
 * Output: 1
 */
class MinimumNumberOfTapsToOpenToWaterAGarden {
    public int minTaps(int n, int[] ranges) {
        int[][] ran = new int[ranges.length][2];
        for (int i = 0; i < ranges.length; i++) {
            ran[i][0] = i - ranges[i];
            ran[i][1] = i + ranges[i];
        }
        Arrays.sort(ran, (a, b) -> {
            if (a[0] == b[0]) return a[1] - b[1];
            else return a[0] - b[0];
        });
        if (ran[0][0] > 0) return -1;
        int i = 0;
        int cnt = 0;
        int maxReach = 0;
        while (i < ran.length && maxReach < n) {
            int tmp = maxReach;
            while (i < ran.length && ran[i][0] <= maxReach) {
                if (ran[i][1] > tmp)
                    tmp = ran[i][1];
                i++;
            }
            if (tmp == maxReach) {
                return -1;
            }
            maxReach = tmp;
            cnt++;
        }
        if (maxReach < n) return -1;
        return cnt;
    }
}
