package per.cc.algo.bit_manipulation;

/**
 * Given a non-empty array of integers, every element appears three times except for one, which appears exactly once.
 * Find that single one.
 *
 * Note:
 *
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 *
 * Example 1:
 *
 * Input: [2,2,3,2]
 * Output: 3
 * Example 2:
 *
 * Input: [0,1,0,1,0,1,99]
 * Output: 99
 */
public class SingleNumber {
    // solution : https://leetcode.com/articles/single-number-ii/
    public int singleNumber(int[] nums) {
        int seeOnce = 0, seeTwice = 0;
        for (int x : nums){
            seeOnce = ~seeTwice & (seeOnce^x);
            seeTwice = ~seeOnce & (seeTwice^x);
        }
        return seeOnce;
    }
}
