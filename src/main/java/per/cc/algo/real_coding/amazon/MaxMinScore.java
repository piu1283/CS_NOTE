package per.cc.algo.real_coding.amazon;

public class MaxMinScore {
    public static int getMaxScore(int[][] grid) {
        if (grid == null || grid.length == 0)
            return 0;
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = Integer.MAX_VALUE;

        for (int i = 1; i < m; i++) dp[i][0] = Math.min(grid[i][0], dp[i - 1][0]);

        for (int j = 1; j < n; j++) dp[0][j] = Math.min(grid[0][j], dp[0][j - 1]);

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                int upper = Math.min(grid[i][j], dp[i - 1][j]);
                int left = Math.min(grid[i][j], dp[i][j - 1]);
                dp[i][j] = Math.max(upper, left);
            }
        }

        return Math.max(dp[m - 1][n - 2], dp[m - 2][n - 1]);
    }
}
