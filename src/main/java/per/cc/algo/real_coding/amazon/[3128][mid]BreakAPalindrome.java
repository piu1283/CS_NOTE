package per.cc.algo.real_coding.amazon;

/**
 * https://leetcode.com/problems/break-a-palindrome/
 * Given a palindromic string palindrome, replace exactly one character by any lowercase English letter so that the
 * string becomes the lexicographically smallest possible string that isn't a palindrome.
 *
 * After doing so, return the final string.  If there is no way to do so, return the empty string.
 *
 *
 *
 * Example 1:
 *
 * Input: palindrome = "abccba"
 * Output: "aaccba"
 * Example 2:
 *
 * Input: palindrome = "a"
 * Output: ""
 *
 *
 * Constraints:
 *
 * 1 <= palindrome.length <= 1000
 * palindrome consists of only lowercase English letters.
 */
class BreakAPalindrome {
    public String breakPalindrome(String palindrome) {
        int n = palindrome.length();
        char[] arr = palindrome.toCharArray();
        if(n == 1) return "";
        int mid = (n - 1) / 2;
        if(n % 2 == 0){
            for(int i = 0; i < n; i++){
                if(arr[i] != 'a'){
                    arr[i] = 'a';
                    return String.valueOf(arr);
                }
            }
            arr[n - 1] = 'b';
            return String.valueOf(arr);
        }else{
            for(int i = 0; i < mid; i++){
                if(arr[i] != 'a'){
                    arr[i] = 'a';
                    return String.valueOf(arr);
                }
            }
            for(int i = mid + 1; i < n; i++){
                if(arr[i] != 'a'){
                    arr[i] = 'a';
                    return String.valueOf(arr);
                }
            }
            arr[n - 1] = 'b';
            return String.valueOf(arr);
        }
    }
}
