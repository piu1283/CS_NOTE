package per.cc.tmplate.string.substring_find;

import java.util.HashMap;

/**
 * Created by Chen on 6/17/20.
 */
public class RollingHash {
    
    private static String hasDuplicate(String S, int len){
        int n = S.length();
        HashMap<Long, String> map = new HashMap<>();
        int a = 26;
        long modulus = (long) Math.pow(2, 32);
        long h = 0L;
        for (int i = 0; i < len; ++i) {
            h = (h * a + charToInt(i, S)) % modulus;
        }
        map.put(h, S.substring(0,len));
        long aL = 1;
        for (int i = 1; i <= len; ++i) aL = (aL * a) % modulus;
        for (int start = 1; start < n - len + 1; ++start) {
            // compute rolling hash in O(1) time
            h = (h * a - charToInt(start - 1, S) * aL % modulus + modulus) % modulus;
            h = (h + charToInt(start + len - 1, S)) % modulus;
            if (map.containsKey(h)){
                return map.get(h);
            }
            map.put(h, S.substring(start,start + len));;
        }
        return "";
    }

    public static int charToInt(int idx, String s) {
        return (int) s.charAt(idx) - (int) 'a';
    }

    public static int strStr(String haystack, String needle) {
        int L = needle.length(), n = haystack.length();
        if (L > n) return -1;

        // base value for the rolling hash function
        int a = 26;
        // modulus value for the rolling hash function to avoid overflow
        long modulus = (long) Math.pow(2, 31);

        // compute the hash of strings haystack[:L], needle[:L]
        long h = 0, ref_h = 0;
        for (int i = 0; i < L; ++i) {
            h = (h * a + charToInt(i, haystack)) % modulus;
            ref_h = (ref_h * a + charToInt(i, needle)) % modulus;
        }
        if (h == ref_h) return 0;

        // const value to be used often : a**L % modulus
        long aL = 1;
        for (int i = 1; i <= L; ++i) aL = (aL * a) % modulus;

        for (int start = 1; start < n - L + 1; ++start) {
            // compute rolling hash in O(1) time
            h = (h * a - charToInt(start - 1, haystack) * aL % modulus + modulus)% modulus;
            h =  (h + charToInt(start + L - 1, haystack)) % modulus;
            if (h == ref_h) return start;
        }
        return -1;
    }
}
