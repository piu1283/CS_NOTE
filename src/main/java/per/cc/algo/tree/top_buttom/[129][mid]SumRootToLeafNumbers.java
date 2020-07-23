package per.cc.algo.tree.top_buttom;

import per.cc.util.TreeNode;

/**
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
 * <p>
 * An example is the root-to-leaf path 1->2->3 which represents the number 123.
 * <p>
 * Find the total sum of all root-to-leaf numbers.
 * <p>
 * Note: A leaf is a node with no children.
 * <p>
 * Example:
 * <p>
 * Input: [1,2,3]
 * 1
 * / \
 * 2   3
 * Output: 25
 * Explanation:
 * The root-to-leaf path 1->2 represents the number 12.
 * The root-to-leaf path 1->3 represents the number 13.
 * Therefore, sum = 12 + 13 = 25.
 * Example 2:
 * <p>
 * Input: [4,9,0,5,1]
 * 4
 * / \
 * 9   0
 * / \
 * 5   1
 * Output: 1026
 * Explanation:
 * The root-to-leaf path 4->9->5 represents the number 495.
 * The root-to-leaf path 4->9->1 represents the number 491.
 * The root-to-leaf path 4->0 represents the number 40.
 * Therefore, sum = 495 + 491 + 40 = 1026.
 */
class SumRootToLeafNumbers {
    // each number will perform multiple times
    public int sumNumbers(TreeNode root) {
        // top-buttom
        if (root == null) {
            return 0;
        }
        return helper(root, 0);
    }

    // strange way
    private int helper(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        sum *= 10;
        sum += root.val;
        int t1 = helper(root.left, sum);
        int t2 = helper(root.right, sum);
        if (t1 == 0 && t2 == 0) {
            return sum;
        } else {
            return t1 + t2;
        }
    }

    // a little bit normal
    int sum = 0;

    private void help2(TreeNode root, int pre) {
        if (root == null) return;
        pre = pre * 10 + root.val;
        if (root.left == null && root.right == null) {
            sum += pre;
        } else {
            help2(root.left, pre);
            help2(root.right, pre);
        }
    }
}
