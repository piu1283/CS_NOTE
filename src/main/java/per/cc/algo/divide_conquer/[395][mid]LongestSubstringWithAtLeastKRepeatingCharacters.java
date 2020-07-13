package per.cc.algo.divide_conquer;

/**
 * Find the length of the longest substring T of a given string (consists of lowercase letters only) such that every
 * character in T appears no less than k times.
 *
 * Example 1:
 *
 * Input:
 * s = "aaabb", k = 3
 *
 * Output:
 * 3
 *
 * The longest substring is "aaa", as 'a' is repeated 3 times.
 * Example 2:
 *
 * Input:
 * s = "ababbc", k = 2
 *
 * Output:
 * 5
 *
 * The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
 */
class LongestSubstringWithAtLeastKRepeatingCharacters {
    /**
     * this problem using two-pointers and divide & conquer
     * Explain video: https://www.youtube.com/watch?v=GU-03VY12Ic
     * @param s
     * @param k
     * @return
     */
    public int longestSubstring(String s, int k) {
        if (s.equals("") || k == 0){
            return 0;
        }
        int [] arr = new int[26];
        for (char c : s.toCharArray()) arr[c - 'a']++;
        boolean full = true;
        for(int i : arr){
            if(i > 0 && i < k){
                full = false;
            }
        }
        if (full) return s.length();
        int l = 0;
        int r = 0;
        int ret = 0;
        while(r < s.length()){
            if(arr[s.charAt(r) - 'a'] >= k){
                r++;
                continue;
            }else{
                ret = Math.max(ret, longestSubstring(s.substring(l,r),k));
                l = r + 1;
            }
            r++;
        }
        ret = Math.max(ret,longestSubstring(s.substring(l),k));
        return ret;
    }
}
