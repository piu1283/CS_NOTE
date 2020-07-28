package per.cc.algo.DP.double_sequence.LCS;

/**
 * Given two strings str1 and str2, return the shortest string that has both str1 and str2 as subsequences.  If
 * multiple answers exist, you may return any of them.
 *
 * (A string S is a subsequence of string T if deleting some number of characters from T (possibly 0, and the
 * characters are chosen anywhere from T) results in the string S.)
 *
 *
 *
 * Example 1:
 *
 * Input: str1 = "abac", str2 = "cab"
 * Output: "cabac"
 * Explanation:
 * str1 = "abac" is a subsequence of "cabac" because we can delete the first "c".
 * str2 = "cab" is a subsequence of "cabac" because we can delete the last "ac".
 * The answer provided is the shortest such string that satisfies these properties.
 *
 *
 * Note:
 *
 * 1 <= str1.length, str2.length <= 1000
 * str1 and str2 consist of lowercase English letters.
 */
class ShortestCommonSupersequence {
    public String shortestCommonSupersequence(String str1, String str2) {
        // find the lcs length
        int[][] dp = new int[str1.length() +1][str2.length()+1];

        for(int i = 0; i <= str1.length(); i++) {
            for(int j = 0; j <= str2.length(); j++) {
                if(i == 0 || j == 0) dp[i][j] = 0;
                else if(str1.charAt(i-1) == str2.charAt(j-1)) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        // start to tracing back, the tracing back process is perfect!
        StringBuilder sb = new StringBuilder();
        int i = str1.length();
        int j = str2.length();
        while(i > 0 && j > 0){
            if(str1.charAt(i - 1) == str2.charAt(j - 1)){
                sb.append(str1.charAt(i - 1));
                i--;
                j--;
            }else if(dp[i - 1][j] > dp[i][j - 1]){
                sb.append(str1.charAt(i - 1));
                i--;
            }else{
                sb.append(str2.charAt(j - 1));
                j--;
            }
        }
        while(i > 0){
            sb.append(str1.charAt(i - 1));
            i--;
        }

        while(j > 0){
            sb.append(str2.charAt(j - 1));
            j--;
        }

        sb.reverse();
        return sb.toString();
    }
}
