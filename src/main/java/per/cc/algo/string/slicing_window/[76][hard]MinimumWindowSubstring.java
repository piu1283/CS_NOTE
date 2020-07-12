package per.cc.algo.string.slicing_window;

import java.util.HashMap;

/**
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in
 * complexity O(n).
 *
 * Example:
 *
 * Input: S = "ADOBECODEBANC", T = "ABC"
 * Output: "BANC"
 * Note:
 *
 * If there is no such window in S that covers all characters in T, return the empty string "".
 * If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
 */
class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        if (s.equals("") || t.equals("")){
            return "";
        }
        char[] sarr = s.toCharArray();
        char[] tarr = t.toCharArray();
        HashMap<Character, Integer> map = new HashMap<>();
        int len = Integer.MAX_VALUE;
        int resStart = 0;
        for(char c:tarr){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int l = 0;
        int r = -1;
        while(r < s.length()){
            if (r - l + 1 < t.length() || !containsT(map)){
                // if not satisfy, put sarr[r], and move r
                r++;
                if (r == s.length()){
                    break;
                }
                if(map.containsKey(sarr[r])){
                    map.put(sarr[r], map.get(sarr[r]) - 1);
                }
            }else{
                // if satisfy, update res
                if(len > r - l + 1){
                    resStart = l;
                    len = r - l + 1;
                }
                // move l, to short the len
                if(map.containsKey(sarr[l])){
                    map.put(sarr[l], map.get(sarr[l]) + 1);
                }
                l++;
            }
        }
        if (len == Integer.MAX_VALUE){
            return "";
        }
        return s.substring(resStart, resStart + len);
    }

    private boolean containsT(HashMap<Character, Integer> map){
        for(int val : map.values()){
            if(val > 0){
                return false;
            }
        }
        return true;
    }
}
