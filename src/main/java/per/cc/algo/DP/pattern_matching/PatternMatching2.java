package per.cc.algo.DP.pattern_matching;

/**
 * 请实现一个函数用来匹配包括'?'和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'可以表示任意字符（包含空字符）。
 */
public class PatternMatching2 {
    public static boolean match(char[] str, char[] pattern) {
        boolean[][] dp = new boolean[pattern.length + 1][str.length + 1];
        dp[0][0] = true;
        for (int i = 1; i <= pattern.length; i++)
            if (pattern[i - 1] == '*')
                dp[i][0] = dp[i][0] && dp[i - 1][0];
        for (int i = 1; i <= pattern.length; i++) {
            for (int j = 1; j <= str.length; j++) {
                if (pattern[i - 1] == str[j - 1] || pattern[i - 1] == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                if (pattern[i - 1] == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1] || dp[i - 1][j - 1];
                }
            }
        }
        return dp[pattern.length][str.length];
    }
}
