package per.cc.algo.DP.sell_stock;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 *
 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and
 * sell one share of the stock multiple times) with the following restrictions:
 *
 * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 * After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
 * Example:
 *
 * Input: [1,2,3,0,2]
 * Output: 3
 * Explanation: transactions = [buy, sell, cooldown, buy, sell]
 */
class BestTimeToBuyAndSellStockWithCooldown {
    // using one array
    // recommended
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        int [] dp = new int[prices.length];
        dp[0] = 0;
        int buy = -prices[0];
        dp[1] = Math.max(0, buy + prices[1]);
        buy = Math.max(-prices[1], buy);
        for(int i = 2; i < prices.length; i++){
            dp[i] = Math.max(dp[i - 1], buy + prices[i]);
            buy = Math.max(buy, dp[i - 2] - prices[i]);
        }
        return dp[prices.length - 1];
    }

    // using two array
    public int maxProfit_(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        //buy[i] - max profit until index i in [0, i] where we did a buy and still own the stock.
        //sell[i] - max profit until index i in [0, i] where we don't own any stock.
        int [] sell = new int [prices.length];
        int [] buy = new int [prices.length];
        int n = prices.length;
        buy[0] = -prices[0];
        buy[1] = Math.max(buy[0], -prices[1]);
        sell[0] = 0;
        sell[1] = Math.max(sell[0], prices[1] + buy[0]);
        for(int i = 2; i < n; i++){
            buy[i] = Math.max(
                    buy[i - 1],
                    sell[i - 2] - prices[i]
            );
            sell[i] = Math.max(
                    sell[i - 1],
                    prices[i] + buy[i - 1]
            );
        }
        return sell[n - 1];
    }
}
