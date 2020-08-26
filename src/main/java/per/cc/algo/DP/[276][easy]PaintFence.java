package per.cc.algo.DP;

/**
 * There is a fence with n posts, each post can be painted with one of the k colors.
 * <p>
 * You have to paint all the posts such that no more than two adjacent fence posts have the same color.
 * <p>
 * Return the total number of ways you can paint the fence.
 * <p>
 * Note:
 * n and k are non-negative integers.
 * <p>
 * Example:
 * <p>
 * Input: n = 3, k = 2
 * Output: 6
 * Explanation: Take c1 as color 1, c2 as color 2. All possible ways are:
 * <p>
 * post1  post2  post3
 * -----      -----  -----  -----
 * 1         c1     c1     c2
 * 2         c1     c2     c1
 * 3         c1     c2     c2
 * 4         c2     c1     c1
 * 5         c2     c1     c2
 * 6         c2     c2     c1
 */
class PaintFence {
    public int numWays(int n, int k) {
        if (n == 0 || k == 0) {
            return 0;
        }
        if (n < 2) {
            return k;
        }
        int[][] dp = new int[n][2];
        dp[0][0] = k;
        dp[0][1] = k;
        dp[1][1] = k;
        dp[1][0] = k * (k - 1);
        for (int i = 2; i < n; i++) {
            dp[i][1] = dp[i - 1][0];
            dp[i][0] = (dp[i - 1][1] + dp[i - 1][0]) * (k - 1);
        }
        return dp[n - 1][0] + dp[n - 1][1];
    }
}
