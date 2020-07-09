package per.cc.algo.wiggle_array;

/**
 * Created by Chen on 6/12/20.
 * <p>
 * https://leetcode.com/problems/wiggle-subsequence/
 * <p>
 * A sequence of numbers is called a wiggle sequence if the differences between successive numbers strictly alternate
 * between positive and negative. The first difference (if one exists) may be either positive or negative. A sequence
 * with fewer than two elements is trivially a wiggle sequence.
 * <p>
 * For example, [1,7,4,9,2,5] is a wiggle sequence because the differences (6,-3,5,-7,3) are alternately positive and
 * negative. In contrast, [1,4,7,2,5] and [1,7,4,5,5] are not wiggle sequences, the first because its first two
 * differences are positive and the second because its last difference is zero.
 * <p>
 * Given a sequence of integers, return the length of the longest subsequence that is a wiggle sequence. A
 * subsequence is obtained by deleting some number of elements (eventually, also zero) from the original sequence,
 * leaving the remaining elements in their original order.
 * <p>
 * Example 1:
 * <p>
 * Input: [1,7,4,9,2,5]
 * Output: 6
 * Explanation: The entire sequence is a wiggle sequence.
 * Example 2:
 * <p>
 * Input: [1,17,5,10,13,15,10,5,16,8]
 * Output: 7
 * Explanation: There are several subsequences that achieve this length. One is [1,17,10,13,10,16,8].
 * Example 3:
 * <p>
 * Input: [1,2,3,4,5,6,7,8,9]
 * Output: 2
 */
class WiggleSubsequence {
    /**
     * DP method
     * @param nums
     * @return
     */
    public int wiggleMaxLength_dp(int[] nums) {
        // dp[i][0] means lonest sequence where i goes up
        // dp[i][1] means lonest sequence where i goes down
        if(nums.length <= 1){
            return nums.length;
        }
        int[][] dp = new int[nums.length][2];
        dp[0][0] = 1;
        dp[0][1] = 1;
        for(int i = 1; i < nums.length; i++){
            if(nums[i] > nums[i - 1]){
                dp[i][0] = dp[i - 1][1] + 1;
                dp[i][1] = dp[i - 1][1];
            }else if(nums[i] < nums[i - 1]){
                dp[i][0] = dp[i - 1][0];
                dp[i][1] = dp[i - 1][0] + 1;
            }else{
                dp[i][0] = dp[i - 1][0];
                dp[i][1] = dp[i - 1][1];
            }
        }
        return Math.max(dp[nums.length - 1][0],dp[nums.length - 1][1]);
    }

    /**
     * a consice version of dp
     * @param nums
     * @return
     */
    public int wiggleMaxLength_dp_consice(int[] nums) {
        if (nums.length < 2)
            return nums.length;
        int down = 1, up = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1])
                up = down + 1;
            else if (nums[i] < nums[i - 1])
                down = up + 1;
        }
        return Math.max(down, up);
    }

    /**
     * greedy method
     * @param nums
     * @return
     */
    public int wiggleMaxLength_greedy(int[] nums) {
        if (nums.length < 2)
            return nums.length;
        int prevdiff = nums[1] - nums[0];
        int count = prevdiff != 0 ? 2 : 1;
        for (int i = 2; i < nums.length; i++) {
            int diff = nums[i] - nums[i - 1];
            if ((diff > 0 && prevdiff <= 0) || (diff < 0 && prevdiff >= 0)) {
                count++;
                prevdiff = diff;
            }
        }
        return count;
    }
}
