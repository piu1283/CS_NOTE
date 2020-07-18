package per.cc.algo.string.palindrome;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/palindrome-partitioning/
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * <p>
 * Return all possible palindrome partitioning of s.
 * <p>
 * Example:
 * <p>
 * Input: "aab"
 * Output:
 * [
 * ["aa","b"],
 * ["a","a","b"]
 * ]
 */
class PalindromePartitioning {
    // backtracking method
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        if (s == null || s.length() == 0) return res;
        helper(res, new ArrayList<>(), 0, s);
        return res;
    }

    private void helper(List<List<String>> res, List<String> tmp, int start, String s) {
        if (start == s.length()) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = start; i < s.length(); i++) {
            if (isP(s, start, i)) {
                String t = s.substring(start, i + 1);
                tmp.add(t);
                helper(res, tmp, i + 1, s);
                tmp.remove(tmp.size() - 1);
            }
        }
    }

    boolean isP(String s, int st, int e) {
        while (st < e) {
            if (s.charAt(st++) != s.charAt(e--))
                return false;
        }
        return true;
    }
}

/**
 * https://leetcode.com/problems/palindrome-partitioning-ii/
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 *
 * Return the minimum cuts needed for a palindrome partitioning of s.
 *
 * Example:
 *
 * Input: "aab"
 * Output: 1
 * Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
 */

class PalindromePartitioningII {
    // dp[i]: the min cut number from [0...i]
    // isPal[i][j]: whether the [i...j] is palindrome
    // dp[i] = min(dp[0..j] + [j + 1, i]) {j <= i}
    // for the [j + 1, i], if it is palindrome, return 1, else return i - j
    public int minCut(String s) {
        // edge cases
        if(s.equals("")){
            return 0;
        }
        int [] dp = new int [s.length()];
        boolean [][] isPal = new boolean[s.length()][s.length()];
        // aaba ac
        // ij
        // dp[j] + i - j[if [i..j] is not pal]
        for(int i = 0; i < s.length(); i++){
            isPal[i][i] = true;
        }
        for(int i = 0; i < s.length(); i++){
            int min = Integer.MAX_VALUE;
            for(int j = 0; j < i; j++){
                if(s.charAt(i) == s.charAt(j)){
                    if(i - 1 < j + 1){
                        isPal[j][i] = true;
                    }else{
                        isPal[j][i] = isPal[j + 1][i - 1];
                    }
                }
                if (isPal[j][i]) {
                    if(j == 0) min = 0;
                    else min = Math.min(min, dp[j - 1] + 1);
                }
                min = Math.min(min, dp[j] + i - j);
            }
            dp[i] = min == Integer.MAX_VALUE ? 0 : min;
        }
        return dp[s.length() - 1];
    }
}
