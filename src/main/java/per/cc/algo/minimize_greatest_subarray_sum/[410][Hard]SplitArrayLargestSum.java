package per.cc.algo.minimize_greatest_subarray_sum;

/**
 * https://leetcode.com/problems/split-array-largest-sum/
 *
 * Given an array which consists of non-negative integers and an integer m, you can split the array into m non-empty
 * continuous subarrays. Write an algorithm to minimize the largest sum among these m subarrays.
 *
 * Note:
 * If n is the length of array, assume the following constraints are satisfied:
 *
 * 1 ≤ n ≤ 1000
 * 1 ≤ m ≤ min(50, n)
 * Examples:
 *
 * Input:
 * nums = [7,2,5,10,8]
 * m = 2
 *
 * Output:
 * 18
 *
 * Explanation:
 * There are four ways to split nums into two subarrays.
 * The best way is to split it into [7,2,5] and [10,8],
 * where the largest sum among the two subarrays is only 18.
 */
class SplitArrayLargestSum {
    public int splitArray(int[] nums, int m) {
        long l = 0l;
        long r = 0l;
        int n = nums.length;
        for(int i = 0; i < n; i++){
            r += nums[i];
            if (l < nums[i]){
                l = nums[i];
            }
        }
        long ans = r;
        while(l <= r){
            long mid = l + (r - l) / 2;
            int groupNum = canWork(nums, mid);
            if (groupNum <= m){
                ans = mid;
                r = mid - 1;
            }else{
                l = mid + 1;
            }
        }
        return (int) ans;

    }

    // will return the min number of group it split under the largest subarray sum less than the threshold
    private int canWork (int [] nums, long threshold){
        int cnt = 1;
        long sum = 0;
        for (int i = 0; i < nums.length; i++){
            if(sum + nums[i] > threshold){
                cnt++;
                sum = nums[i];
            }else{
                sum += nums[i];
            }
        }
        return cnt;
    }
}
