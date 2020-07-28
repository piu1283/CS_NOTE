package per.cc.algo.DP.double_sequence;

/**
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
 * <p>
 * Example 1:
 * <p>
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * Output: true
 * Example 2:
 * <p>
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * Output: false
 */
class InterleavingString {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s3.length() != s1.length() + s2.length()) {
            return false;
        }
        int len1 = s1.length();
        int len2 = s2.length();
        boolean [][] dp = new boolean[len1 + 1][len2 + 1];
        for(int i = 0; i <= len1; i++){
            for(int j = 0; j <= len2; j++){
                if(i == 0 && j == 0){
                    dp[i][j] = true;
                    continue;
                }
                if(i == 0){
                    dp[i][j] = (s2.charAt(j - 1) == s3.charAt(j - 1) && dp[i][j - 1]);
                }else if(j == 0){
                    dp[i][j] = (s1.charAt(i - 1) == s3.charAt(i - 1) && dp[i - 1][j]);
                }else{
                    dp[i][j] = (s2.charAt(j - 1) == s3.charAt(i + j - 1) && dp[i][j - 1]) || (s1.charAt(i - 1) == s3.charAt(i + j - 1) && dp[i - 1][j]);
                }
            }
        }
        return dp[len1][len2];
    }
}
