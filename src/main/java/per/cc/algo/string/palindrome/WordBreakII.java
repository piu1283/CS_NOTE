package per.cc.algo.string.palindrome;

import java.util.*;

/**
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to
 * construct a sentence where each word is a valid dictionary word. Return all such possible sentences.
 *
 * Note:
 *
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 * Example 1:
 *
 * Input:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * Output:
 * [
 *   "cats and dog",
 *   "cat sand dog"
 * ]
 * Example 2:
 *
 * Input:
 * s = "pineapplepenapple"
 * wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 * Output:
 * [
 *   "pine apple pen apple",
 *   "pineapple pen apple",
 *   "pine applepen apple"
 * ]
 * Explanation: Note that you are allowed to reuse a dictionary word.
 * Example 3:
 *
 * Input:
 * s = "catsandog"
 * wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output:
 * []
 */
class WordBreakII {
    // if you use backtracking, it will definitely TLE
    // So, use dfs with mem, or DP
    public List<String> wordBreak(String s, List<String> wordDict) {
        if(s.equals("")){
            return new ArrayList<>();
        }
        Set<String> set = new HashSet<>(wordDict);
        HashMap<Integer,List<String>> mem = new HashMap<>();
        List<String> res = helper(mem, set, s, 0);
        return res;
    }

    private List<String> helper(Map<Integer, List<String>> mem, Set<String> wSet, String s, int stIdx){
        if(stIdx == s.length()){
            return Arrays.asList("");
        }
        if(mem.get(stIdx) != null){
            return mem.get(stIdx);
        }
        List<String> curList = new ArrayList<>();
        for(int i = stIdx; i < s.length(); i++){
            String sub = s.substring(stIdx, i + 1);
            if(wSet.contains(sub)){
                List<String> tmp = helper(mem, wSet, s, i + 1);
                for(String cs : tmp){
                    if(cs.equals("")){
                        curList.add(sub);
                    }else{
                        curList.add(sub + " " + cs);
                    }
                }
            }
        }
        mem.put(stIdx, curList);
        return curList;
    }
}
