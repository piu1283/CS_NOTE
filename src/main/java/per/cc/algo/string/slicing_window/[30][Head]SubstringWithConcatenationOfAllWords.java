package per.cc.algo.string.slicing_window;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices
 * of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening
 * characters.
 *
 *
 *
 * Example 1:
 *
 * Input:
 *   s = "barfoothefoobarman",
 *   words = ["foo","bar"]
 * Output: [0,9]
 * Explanation: Substrings starting at index 0 and 9 are "barfoo" and "foobar" respectively.
 * The output order does not matter, returning [9,0] is fine too.
 * Example 2:
 *
 * Input:
 *   s = "wordgoodgoodgoodbestword",
 *   words = ["word","good","best","word"]
 * Output: []
 */
class SubstringWithConcatenationOfAllWords {
    public List<Integer> findSubstring(String s, String[] words) {
        if (words.length < 1 || s.equals("")){
            return new ArrayList<>();
        }
        HashMap<String, Integer> map = new HashMap<>();
        for(String st : words){
            map.put(st, map.getOrDefault(st,0) + 1);
        }
        int wLen = words[0].length();
        int wNum = words.length;
        int l = 0;
        int r = l + wLen * wNum - 1;
        List<Integer> res = new ArrayList<>();
        while(r < s.length()){
            HashMap<String, Integer> copyMap = new HashMap<>(map);
            if(containsAll(s, l, r, wLen,wNum, copyMap)){
                res.add(l);
            }
            l++;
            r++;
        }
        return res;
    }

    private boolean containsAll(String s, int start, int end, int wLen,int wNum, HashMap<String, Integer> map){
        if(end - start + 1 != wNum * wLen){
            return false;
        }
        int idx = start;
        while(idx <= end){
            String tmp = s.substring(idx, idx + wLen);
            if(map.containsKey(tmp)){
                map.put(tmp, map.get(tmp) - 1);
                if(map.get(tmp) < 0){
                    return false;
                }
            }else{
                return false;
            }
            idx = idx + wLen;
        }
        for(int val : map.values()){
            if(val != 0){
                return false;
            }
        }
        return true;
    }
}
