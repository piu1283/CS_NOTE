package per.cc.algo.binary_index_tree;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/count-of-smaller-numbers-after-self/
 * <p>
 * You are given an integer array nums and you have to return a new counts
 * array. The counts array has the property where counts[i] is the number of
 * smaller elements to the right of nums[i].
 * <p>
 * Example:
 * <p>
 * Input: [5,2,6,1] Output: [2,1,1,0] Explanation: To the right of 5 there are 2
 * smaller elements (2 and 1). To the right of 2 there is only 1 smaller element
 * (1). To the right of 6 there is 1 smaller element (1). To the right of 1
 * there is 0 smaller element.
 */

class BITree {
    int[] feq;

    BITree(int size) {
        feq = new int[size];
    }

    public void update(int pos, int val) {
        while (pos < feq.length) {
            feq[pos] += val;
            pos += getLowBit(pos);
        }
    }

    public int query(int i) {
        int sum = 0;
        while (i > 0) {
            sum += feq[i];
            i -= getLowBit(i);
        }
        return sum;
    }

    private int getLowBit(int i) {
        return i & (-i);
    }
}

class CountOfSmallerNumbersAfterSelf {
    // BIT method time O(nlogn) n is the distinct number
    public List<Integer> countSmaller(int[] nums) {
        // first we need to sort this arr and eliminate the same elements
        TreeSet<Integer> sortedSet = new TreeSet<>();
        for (int i : nums) {
            sortedSet.add(i);
        }
        HashMap<Integer, Integer> rankMap = new HashMap<>();
        int rank = 1;
        for (int i : sortedSet) {
            rankMap.put(i, rank++);
        }
        BITree tree = new BITree(nums.length + 1);
        int[] res = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            tree.update(rankMap.get(nums[i]), 1);
            res[i] = tree.query(rankMap.get(nums[i]) - 1);
        }
        return Arrays.stream(res).boxed().collect(Collectors.toList());
    }
}