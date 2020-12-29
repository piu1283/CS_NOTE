package per.cc.algo.real_coding.bytedance;

public class KillMonster {
    public static void main(String[] args) {
        System.out.println(RPG(3, 1, 6));
    }

    private static double RPG(int n, int m, int k) {
        double[][] dp = new double[n][k + 1];
        dp[0][0] = 1;
        double p = 1.0 / (m + 1);
        for (int i = 1; i <= k; ++i)
            for (int j = 0; j < n; ++j) {
                for (int l = 0; j >= l && l <= m; ++l) {
                    dp[j][i] += dp[j - l][i - 1] * p;
                }
            }
        double rst = 0.0;
        for (int i = 0; i < n; ++i) {
            rst += dp[i][k];
        }
        return 1.0 - rst;
    }
}
