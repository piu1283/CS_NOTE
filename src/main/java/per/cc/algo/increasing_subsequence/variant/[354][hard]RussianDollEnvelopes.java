package per.cc.algo.increasing_subsequence.variant;

import java.util.Arrays;

/**
 * You have a number of envelopes with widths and heights given as a pair of integers (w, h). One envelope can fit
 * into another if and only if both the width and height of one envelope is greater than the width and height of the
 * other envelope.
 *
 * What is the maximum number of envelopes can you Russian doll? (put one inside other)
 *
 * Note:
 * Rotation is not allowed.
 *
 * Example:
 *
 * Input: [[5,4],[6,4],[6,7],[2,3]]
 * Output: 3
 * Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
 */
class RussianDollEnvelopes {
    // this is a hard problem which can be solved using LIS
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes,(a1,a2)->{
            if(a1[0] == a2[0]){
                // if h is the same, the w should go reverse,
                // so that these two will not both show up in LIS
                return a2[1] - a1[1];
            }
            return a1[0] - a2[0];
        });
        int [] tmp = new int[envelopes.length];
        for(int i = 0; i < envelopes.length; i++){
            tmp[i] = envelopes[i][1];
        }
        int res = LIS(tmp);
        return res;
    }

    private int LIS(int [] nums){
        int [] dp = new int[nums.length];
        int len = 0;
        for(int m : nums){
            int i = Arrays.binarySearch(dp, 0, len, m);
            if( i < 0){
                i = -(i + 1);
            }
            dp[i] = m;
            if(i == len){
                len++;
            }
        }
        return len;
    }
}
