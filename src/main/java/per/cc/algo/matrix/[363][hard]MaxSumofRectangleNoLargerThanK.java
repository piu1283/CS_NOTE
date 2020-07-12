package per.cc.algo.matrix;

import java.util.TreeSet;

/**
 * Created by Chen on 7/10/20.
 */
class MaxSumofRectangleNoLargerThanK {
    int res = Integer.MIN_VALUE;

    public int maxSumSubmatrix_(int[][] matrix, int k) {
        int row = matrix.length;
        int col = matrix[0].length;
        // compress the row
        // a1,a2,a3,a4,a5
        // b1,b2,b3,b4,b5
        // c1,c2,c3,c4,c5
        // d1,d2,d3,d4,d5
        // compress....a-c
        // a1+b1+c1, a2+b2+c2,....,a5+b5+c5
        // then we just need to get the max subarray no larger than k in the comprass array
        int[][] rowSum = new int[matrix.length + 1][col];
        rowSum[1] = matrix[0];
        for (int i = 2; i < rowSum.length; i++) {
            for (int j = 0; j < col; j++) {
                rowSum[i][j] = matrix[i - 1][j] + rowSum[i - 1][j];
            }
        }
        for (int i = 0; i < rowSum.length; i++) {
            for (int j = i + 1; j < rowSum.length; j++) {
                int[] s = new int[col];
                for (int p = 0; p < col; p++) {
                    s[p] = rowSum[j][p] - rowSum[i][p];
                }
                getSumLessK(s, k);
                if (res == k) {
                    return res;
                }
            }
        }
        if (res == Integer.MIN_VALUE) {
            return -1;
        }
        return res;
    }

    private void getSumLessK(int[] sum, int k) {
        TreeSet<Integer> tree = new TreeSet<>();
        int acc = 0;
        tree.add(0);
        // sum[i]-sum[j] <= k
        // sum[j] >= sum[i] - k
        for (int i = 0; i < sum.length; i++) {
            acc += sum[i];
            int tmp = acc - k;
            Integer si = tree.ceiling(tmp);
            if (si != null) {
                res = Math.max(acc - si, res);
            }
            tree.add(acc);
        }
    }
}
