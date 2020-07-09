package per.cc.algo.string.form_number;

import java.util.Arrays;

/**
 * Given a list of non negative integers, arrange them such that they form the largest number.
 * <p>
 * Example 1:
 * <p>
 * Input: [10,2]
 * Output: "210"
 * Example 2:
 * <p>
 * Input: [3,30,34,5,9]
 * Output: "9534330"
 * Note: The result may be very large, so you need to return a string instead of an integer.
 */
class LargestNumber {
    public String largestNumber(int[] nums) {
        if (nums.length < 1) {
            return "";
        }
        // check 0
        boolean all0 = true;
        for (int i : nums) {
            if (i != 0) {
                all0 = false;
            }
        }
        if (all0) {
            return "0";
        }
        String[] sArr = new String[nums.length];
        int cnt = 0;
        for (int i : nums) {
            sArr[cnt++] = String.valueOf(i);
        }
        Arrays.sort(sArr, (s1, s2) -> (s2 + s1).compareTo(s1 + s2));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sArr.length; i++) {
            sb.append(sArr[i]);
        }
        return sb.toString();
    }
}
