package per.cc.algo.DP.backpack;

/**
 *
 * Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets
 * such that the sum of elements in both subsets is equal.
 *
 * Note:
 *
 * Each of the array element will not exceed 100.
 * The array size will not exceed 200.
 *
 *
 * Example 1:
 *
 * Input: [1, 5, 11, 5]
 *
 * Output: true
 *
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 *
 *
 * Example 2:
 *
 * Input: [1, 2, 3, 5]
 *
 * Output: false
 *
 * Explanation: The array cannot be partitioned into equal sum subsets.
 */
class PartitionEqualSubsetSum {
    // https://labuladong.gitbook.io/algo/dong-tai-gui-hua-xi-lie/bei-bao-zi-ji
    // this problem can transfer to 0-1 back-pack problem
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int i : nums){
            sum += i;
        }
        if(sum % 2 != 0) return false;
        int mid = sum / 2;
        return can(nums, mid);
    }

    private boolean can(int[] nums, int target){
        boolean [] dp = new boolean [target + 1];
        dp[0] = true;
        for(int i = 1; i < nums.length; i++){
            for(int j = target; j >= 0; j--){
                if(j >= nums[i])
                    dp[j] = dp[j] || dp[j - nums[i]];
                else break;
            }
        }
        return dp[target];
    }
}
