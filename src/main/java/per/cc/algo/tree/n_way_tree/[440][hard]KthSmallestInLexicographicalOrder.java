package per.cc.algo.tree.n_way_tree;

/**
 * Given integers n and k, find the lexicographically k-th smallest integer in the range from 1 to n.
 *
 * Note: 1 ≤ k ≤ n ≤ 109.
 *
 * Example:
 *
 * Input:
 * n: 13   k: 2
 *
 * Output:
 * 10
 *
 * Explanation:
 * The lexicographical order is [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9], so the second smallest number is 10.
 */
class KthSmallestInLexicographicalOrder {
    public int findKthNumber(int n, int k) {
        long cur = 1;
        while(k > 1) {
            long gap = findGap(cur, cur + 1, n);
            if(gap <= k - 1) {
                k -= gap;
                cur = cur + 1;
            }
            else {
                cur = cur * 10;
                k -= 1;
            }
        }

        return (int)cur;
    }

    private long findGap(long a, long b, int n) {
        long gap = 0;
        while(a <= n) {
            gap += Math.min(n + 1, b) - a;
            a = a * 10;
            b = b * 10;
        }
        return gap;
    }
}
