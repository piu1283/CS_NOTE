package per.cc.tmplate.string.palindrome;

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
