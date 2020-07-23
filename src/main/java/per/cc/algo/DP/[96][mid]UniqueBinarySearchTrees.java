package per.cc.algo.DP;

/**
 * Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?
 *
 * Example:
 *
 * Input: 3
 * Output: 5
 * Explanation:
 * Given n = 3, there are a total of 5 unique BST's:
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 *
 *
 * Constraints:  1 <= n <= 19
 */
class UniqueBinarySearchTrees {
    // dp[i] = f[1,n] + f[2,n] + ... + f[i,n]
    // dp[i] : numbers of unique BST can be formed by given i elements
    // f[i,n] : numbers of unique BST can be formed when i as a root and n is the total number of elements
    // f[i,n] = dp[i - 1] * dp[n - i]
    public int numTrees(int n) {
        if(n==0){
            return 0;
        }
        int [] dp =  new int [n+1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i=2;i<=n;i++){
            for(int j=1;j<=i;j++){
                dp[i] += dp[j-1] * dp[i - j];
            }
        }
        return dp[n];
    }
}
