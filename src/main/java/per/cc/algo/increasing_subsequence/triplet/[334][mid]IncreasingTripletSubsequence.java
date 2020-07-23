package per.cc.algo.increasing_subsequence.triplet;

/**
 * Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.
 * <p>
 * Formally the function should:
 * <p>
 * Return true if there exists i, j, k
 * such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
 * Note: Your algorithm should run in O(n) time complexity and O(1) space complexity.
 * <p>
 * Example 1:
 * <p>
 * Input: [1,2,3,4,5]
 * Output: true
 * Example 2:
 * <p>
 * Input: [5,4,3,2,1]
 * Output: false
 */
class Solution {
    // using greedy
    // time O (n), space O(1)
    public boolean increasingTriplet(int[] nums) {
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        for (int num : nums) {
            if (min1 >= num) {
                min1 = num;
            } else if (min2 < num) {
                return true;
            } else {
                min2 = num;
            }
        }
        return false;
    }

    // another method is using two array
    // time O(n) space O(2n)
    public boolean increasingTriplet_twoarr(int[] arr) {
        int n = arr.length;

        // Index of maximum element
        // from right side
        int max = n - 1;

        // Index of minimum element
        // from left side
        int min = 0;
        int i;

        // Create an array that will store
        // index of a smaller element on left side.
        // If there is no smaller element on left
        // side, then smaller[i] will be -1.
        int[] smaller = new int[n];

        // first entry will always be -1
        smaller[0] = -1;
        for (i = 1; i < n; i++) {
            if (arr[i] <= arr[min]) {
                min = i;
                smaller[i] = -1;
            } else
                smaller[i] = min;
        }

        // Create another array that will
        // store index of a greater element
        // on right side. If there is no greater
        // element on right side, then greater[i]
        // will be -1.
        int[] greater = new int[n];

        // last entry will always be -1
        greater[n - 1] = -1;
        for (i = n - 2; i >= 0; i--) {
            if (arr[i] >= arr[max]) {
                max = i;
                greater[i] = -1;
            } else
                greater[i] = max;
        }

        // Now find a number which has
        // both greater number on right
        // side and smaller number on left side
        for (i = 0; i < n; i++) {
            if (smaller[i] != -1 && greater[i] != -1) {
                return true;
            }
        }

        // If we reach number, then there
        // are no such 3 numbers
        return false;
    }
}