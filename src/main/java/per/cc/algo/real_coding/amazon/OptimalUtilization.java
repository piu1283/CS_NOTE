package per.cc.algo.real_coding.amazon;

import java.util.*;

/**
 * https://leetcode.com/discuss/interview-question/373202
 * Given 2 lists a and b. Each element is a pair of integers where the first integer represents the unique id and the
 * second integer represents a value. Your task is to find an element from a and an element form b such that the sum
 * of their values is less or equal to target and as close to target as possible. Return a list of ids of selected
 * elements. If no pair is possible, return an empty list.
 *
 * Example 1:
 *
 * Input:
 * a = [[1, 2], [2, 4], [3, 6]]
 * b = [[1, 2]]
 * target = 7
 *
 * Output: [[2, 1]]
 *
 * Explanation:
 * There are only three combinations [1, 1], [2, 1], and [3, 1], which have a total sum of 4, 6 and 8, respectively.
 * Since 6 is the largest sum that does not exceed 7, [2, 1] is the optimal pair.
 * Example 2:
 *
 * Input:
 * a = [[1, 3], [2, 5], [3, 7], [4, 10]]
 * b = [[1, 2], [2, 3], [3, 4], [4, 5]]
 * target = 10
 *
 * Output: [[2, 4], [3, 2]]
 *
 * Explanation:
 * There are two pairs possible. Element with id = 2 from the list `a` has a value 5, and element with id = 4 from
 * the list `b` also has a value 5.
 * Combined, they add up to 10. Similarily, element with id = 3 from `a` has a value 7, and element with id = 2 from
 * `b` has a value 3.
 * These also add up to 10. Therefore, the optimal pairs are [2, 4] and [3, 2].
 * Example 3:
 *
 * Input:
 * a = [[1, 8], [2, 7], [3, 14]]
 * b = [[1, 5], [2, 10], [3, 14]]
 * target = 20
 *
 * Output: [[3, 1]]
 * Example 4:
 *
 * Input:
 * a = [[1, 8], [2, 15], [3, 9]]
 * b = [[1, 8], [2, 11], [3, 12]]
 * target = 20
 *
 * Output: [[1, 3], [3, 2]]
 */
public class OptimalUtilization {

    public static void main(String[] args) {
        List<List<Integer>> res = ou(new int[][]{{1, 8}, {2, 15}, {3, 9}}, new int[][]{{1, 8}, {2, 11}, {3, 12}}, 20);
        System.out.println(res);
    }

    private static List<List<Integer>> ou(int[][] a, int [][] b, int target){
        TreeMap<Integer, List<Integer>> mapA = new TreeMap<>();
        for (int[] t : a) {
            if (mapA.containsKey(t[1])) {
                mapA.get(t[1]).add(t[0]);
            }else{
                ArrayList<Integer> list = new ArrayList<>();
                list.add(t[0]);
                mapA.put(t[1], list);
            }
        }
        int sum = 0;
        List<List<Integer>> res = new ArrayList<>();
        for (int[] t : b) {
            Map.Entry<Integer, List<Integer>> entry = mapA.floorEntry(target - t[1]);
            if (entry != null) {
                if (sum < entry.getKey() + t[1]) {
                    sum = entry.getKey() + t[1];
                    res.clear();
                }
                if(sum == entry.getKey() + t[1]){
                    for (int aIdx : entry.getValue()) {
                        res.add(Arrays.asList(aIdx, t[0]));
                    }
                }
            }
        }
        return res;
    }

}
