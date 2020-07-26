package per.cc.algo.DP.sell_stock;

/**
 * Created by Chen on 7/23/20.
 */
class BestTimeToBuyAndSellStockIII {
    // https://www.youtube.com/watch?v=oDhu5uGq_ic
    // dp[i][j]: the most profit you can get at day j, in i round transaction
    /*
    dp[i][j] = max(dp[i][j - 1], Max(prices[j] - prices[m] + dp[i - 1][m], m= {0...j - 1}))
    the maxDiff = dp[i - 1][m] - prices[m]
    */
    public int maxProfit(int[] prices) {
        int k = 2 + 1;
        int[][] dp = new int[k][prices.length];
        int maxDiff = Integer.MIN_VALUE;
        for(int i = 1; i < k; i++){
            for(int j = 1; j < prices.length; j++){
                dp[i][j] = Math.max(dp[i][j - 1], prices[j] + maxDiff);
                maxDiff = Math.max(maxDiff, dp[i - 1][j - 1] - prices[j - 1]);
            }
        }
        return dp[k - 1][prices.length - 1];
    }
}
