package per.cc.tmplate.binary_search;

/**
 * Given a sorted array and a target value, return the index if the target is
 * found. If not, return the index where it would be if it were inserted in
 * order.
 *
 * You may assume no duplicates in the array.
 *
 * Example 1:
 *
 * Input: [1,3,5,6], 5 Output: 2 Example 2:
 *
 * Input: [1,3,5,6], 2 Output: 1 Example 3:
 *
 * Input: [1,3,5,6], 7 Output: 4 Example 4:
 *
 * Input: [1,3,5,6], 0 Output: 0
 */
class Solution {
    public int searchInsert(int[] nums, int target) {
        if(nums.length < 1){
            return 0;
        }
        int l = 0;
        int r = nums.length - 1;
        int mid = 0;
        while(l <= r){
            mid = l + (r - l) / 2;
            if(nums[mid] == target){
                return mid;
            }else{
                if(nums[mid] > target){
                    r = mid - 1;
                }else{
                    l = mid + 1;
                }
            }
        }

        return l;
    }
}
