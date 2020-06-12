package per.cc.tmplate.wiggle_array;

import java.util.Arrays;

/**
 * Created by Chen on 6/12/20.
 * https://leetcode.com/problems/wiggle-sort/
 *
 * Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....
 *
 * Example:
 *
 * Input: nums = [3,5,2,1,6,4]
 * Output: One possible answer is [3,5,1,6,2,4]
 *
 */
class WiggleSort {
    /**
     * using sorting
     * @param nums
     */
    public void wiggleSort_sort(int[] nums) {
        Arrays.sort(nums);
        if(nums.length <= 2){
            return;
        }
        int l = 1;
        int r = 2;
        while(r < nums.length){
            if(nums[l] != nums[r]){
                swap(nums, l, r);
            }
            r += 2;
            l += 2;
        }
    }

    /**
     * one-pass (greedy)
     * @param nums
     */
    public void wiggleSort(int[] nums) {
        boolean less = true;
        for(int i = 0; i < nums.length - 1; i++){
            if(less){
                if(nums[i] > nums[i + 1]){
                    swap(nums,i, i + 1);
                }
            }else{
                if(nums[i] < nums[i + 1]){
                    swap(nums, i, i + 1);
                }
            }
            less = !less;
        }
    }

    private void swap(int[] nums, int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
