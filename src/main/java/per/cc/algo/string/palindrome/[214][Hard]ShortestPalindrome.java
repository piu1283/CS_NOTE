package per.cc.algo.string.palindrome;

/**
 * Given a string s, you are allowed to convert it to a palindrome by adding characters in front of it. Find and
 * return the shortest palindrome you can find by performing this transformation.
 * <p>
 * Example 1:
 * <p>
 * Input: "aacecaaa"
 * Output: "aaacecaaa"
 * Example 2:
 * <p>
 * Input: "abcd"
 * Output: "dcbabcd"
 */

/**
 * this problem need us to find the longest palindrome prefix
 * s =     "[a b c c b a] x y"
 * rev_s = "y x [a b c c b a]"
 * the longest palindrome prefix is s[0]-s[5]
 */
class ShortestPalindrome {
    // O(n^2)
    public String shortestPalindrome(String s) {
        if(s.equals("")){
            return "";
        }
        int mid = s.length() / 2;
        for(int i = mid; i >= 0; i--){
            int tmp1 = search(s, i, i);
            if(tmp1 == s.length()){
                return s;
            }else if(tmp1 >= 0){
                return constrRes(s, tmp1);
            }
            if(i > 0){
                int tmp2 = search(s, i - 1, i);
                if(tmp2 == s.length()){
                    return s;
                }else if(tmp2 >= 0){
                    return constrRes(s, tmp2);
                }
            }
        }
        return constrRes(s, 1);
    }

    private String constrRes(String s, int j){
        String tmp = s.substring(j);
        char[] crr = tmp.toCharArray();
        int l = 0;
        int r = crr.length - 1;
        while(l < r){
            char c = crr[l];
            crr[l] = crr[r];
            crr[r] = c;
            l++;
            r--;
        }
        return String.valueOf(crr) + s;
    }

    private int search(String s, int i, int j){
        while(i >= 0 && j < s.length()){
            if(s.charAt(i) == s.charAt(j)){
                i--;
                j++;
            }else{
                break;
            }
        }
        if(i == -1 && j == s.length()){
            return s.length();
        }else if(i == -1){
            return j;
        }else{
            return -1;
        }
    }
}

class RollingHash {
    // O(n), using Rolling Hash
    //https://leetcode.com/problems/shortest-palindrome/discuss/60153/8-line-O(n)-method-using-Rabin-Karp-rolling-hash
    public String shortestPalindrome(String s) {
        int n = s.length(), pos = -1;
        long B = 29, MOD = 1000000007, POW = 1, hash1 = 0, hash2 = 0;
        for (int i = 0; i < n; i++, POW = POW * B % MOD) {
            hash1 = (hash1 * B + s.charAt(i) - 'a' + 1) % MOD;
            hash2 = (hash2 + (s.charAt(i) - 'a' + 1) * POW) % MOD;
            if (hash1 == hash2) pos = i;
        }
        return new StringBuilder().append(s.substring(pos + 1, n)).reverse().append(s).toString();
    }
}

