package per.cc.algo.brain_teaser;

import java.util.Arrays;

/**
 * https://leetcode.com/articles/next-permutation/
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 * <p>
 * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending
 * order).
 * <p>
 * The replacement must be in-place and use only constant extra memory.
 * <p>
 * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand
 * column.
 * <p>
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 */
class NextPermutation {
    // 115, 151, 511
    // 123, 132, 213, 231, 312, 321,
    public void nextPermutation(int[] nums) {
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i] > nums[i - 1]) {
                int j = nums.length - 1;
                while (j >= 0 && nums[j] <= nums[i - 1]) {
                    j--;
                }
                swap(nums, i - 1, j);
                // we only need to reverse from i to end, that will be more faster
                Arrays.sort(nums, i, nums.length);
                return;
            }
        }
        // same in here, only need to reverse
        Arrays.sort(nums);
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

}
