package per.cc.tmplate.string.encode_decode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/encode-and-decode-strings/
 *
 * Design an algorithm to encode a list of strings to a string. The encoded string is then sent over the network and
 * is decoded back to the original list of strings.
 *
 * Machine 1 (sender) has the function:
 *
 * string encode(vector<string> strs) {
 *   // ... your code
 *   return encoded_string;
 * }
 * Machine 2 (receiver) has the function:
 * vector<string> decode(string s) {
 *   //... your code
 *   return strs;
 * }
 * So Machine 1 does:
 *
 * string encoded_string = encode(strs);
 * and Machine 2 does:
 *
 * vector<string> strs2 = decode(encoded_string);
 * strs2 in Machine 2 should be the same as strs in Machine 1.
 *
 * Implement the encode and decode methods.
 *
 *
 *
 * Note:
 *
 * The string may contain any possible characters out of 256 valid ascii characters. Your algorithm should be
 * generalized enough to work on any possible characters.
 * Do not use class member/global/static variables to store states. Your encode and decode algorithms should be
 * stateless.
 * Do not rely on any library method such as eval or serialize methods. You should implement your own encode/decode
 * algorithm.
 */
class EncodeAndDecodeStrings {

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for(String s : strs){
            sb.append(intToString(s));
            sb.append(s);
        }
        return sb.toString();
    }

    private String intToString(String s){
        int x = s.length();
        char[] b = new char[4];
        for(int i = 3; i >= 0; i--){
            b[3 - i] = (char) (x >> (i * 8) & 0xFF);
        }
        return new String(b);
    }

    private int stringToInt(String s){
        int res = 0;
        char [] cs = s.toCharArray();
        for(int i = 0; i < 4; i++){
            res = (res << 8) + (int)cs[i];
        }
        return res;
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> res = new ArrayList<>();
        int i = 0;
        while(i < s.length()){
            int len = stringToInt(s.substring(i, i + 4));
            i += 4;
            res.add(s.substring(i, i + len));
            i += len;
        }
        return res;
    }
}
