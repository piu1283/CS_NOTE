package per.cc.tmplate.longest_increasing_subsequence;

import java.util.Arrays;

/**
 * Created by Chen on 6/16/20.
 */
public class LongestIncreasingSubsequence {
    // nlogn
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int len = 0;
        for (int num : nums) {
            int i = Arrays.binarySearch(dp, 0, len, num);
            if (i < 0) {
                i = -(i + 1);
            }
            dp[i] = num;
            if (i == len) {
                len++;
            }
        }
        return len;
    }

    // n^2
    public int lengthOfLIS_DP(int[] nums) {
        if(nums.length <= 1){
            return nums.length;
        }
        int [] best = new int[nums.length];
        int [] path = new int[nums.length];// for output the longest subarray
        best[0] = 1;
        int totalMax = 0;
        for(int i=1;i<nums.length;i++){
            int max = 0;
            int maxIndex = i;
            for(int j=i-1;j>=0;j--){
                if(nums[i]>nums[j] && best[j] >= max){
                    max = best[j];
                    maxIndex = j;
                }
            }
            best[i] = max+1;
            path[i] = maxIndex;
            totalMax = Math.max(best[i], totalMax);
        }
        return totalMax;
    }
}
