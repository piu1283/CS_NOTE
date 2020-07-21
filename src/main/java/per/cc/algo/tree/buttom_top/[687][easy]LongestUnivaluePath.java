package per.cc.algo.tree.buttom_top;

import per.cc.util.TreeNode;

/**
 * Given a binary tree, find the length of the longest path where each node in the path has the same value. This path
 * may or may not pass through the root.
 * <p>
 * The length of path between two nodes is represented by the number of edges between them.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input:
 * <p>
 * 5
 * / \
 * 4   5
 * / \   \
 * 1   1   5
 * Output: 2
 * <p>
 * <p>
 * <p>
 * Example 2:
 * <p>
 * Input:
 * <p>
 * 1
 * / \
 * 4   5
 * / \   \
 * 4   4   5
 * Output: 2
 * <p>
 * <p>
 * <p>
 * Note: The given binary tree has not more than 10000 nodes. The height of the tree is not more than 1000.
 */
class LongestUnivaluePath {
    int ans = 0;
    public int longestUnivaluePath(TreeNode root) {
        if(root == null){
            return 0;
        }
        helper(root);
        return ans;
    }
    public int helper(TreeNode node) {
        if (node == null) return 0;
        int left = helper(node.left);
        int right = helper(node.right);
        int arrowLeft = 0, arrowRight = 0;
        if (node.left != null && node.left.val == node.val) {
            arrowLeft += left + 1;
        }
        if (node.right != null && node.right.val == node.val) {
            arrowRight += right + 1;
        }
        ans = Math.max(ans, arrowLeft + arrowRight);
        return Math.max(arrowLeft, arrowRight);
    }
}
