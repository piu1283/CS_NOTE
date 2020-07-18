package per.cc.algo.string.slicing_window;

import java.util.HashMap;
import java.util.Optional;

/**
 * Given a string s , find the length of the longest substring t  that contains at most 2 distinct characters.
 *
 * Example 1:
 *
 * Input: "eceba"
 * Output: 3
 * Explanation: t is "ece" which its length is 3.
 * Example 2:
 *
 * Input: "ccaabbb"
 * Output: 5
 * Explanation: t is "aabbb" which its length is 5.
 */
class LongestSubstringWithAtMostTwoDistinctCharacters {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s.equals("")){
            return 0;
        }
        HashMap<Character, Integer> map = new HashMap<>();
        int j = 0;
        int len = 0;
        for(int i = 0; i < s.length(); i++){
            // if the question change to k distinct character, just change 2 to k
            while(j < s.length() && (map.size() < 2 || ( map.size() == 2 && map.containsKey(s.charAt(j))))){
                map.put(s.charAt(j),map.getOrDefault(s.charAt(j), 0) + 1);
                len = Math.max(len, j - i + 1);
                j++;
            }
            if(map.get(s.charAt(i)) - 1 > 0){
                map.put(s.charAt(i), map.get(s.charAt(i)) - 1);
            }else{
                map.remove(s.charAt(i));
            }
        }
        return len;
    }
}
