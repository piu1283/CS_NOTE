package per.cc.algo.tree;

import per.cc.util.TreeNode;

/**
 * Given a binary tree, find its minimum depth.
 *
 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 *
 * Note: A leaf is a node with no children.
 *
 * Example:
 *
 * Given binary tree [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its minimum depth = 2.
 */
class MinimumDepthOfBinaryTree {
    public int minDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        // be careful with this, the edge case is [1,2], should return 2
        // so if the left or right is 0, which means there is no leaf node in that side, we should not use it to compare
        if(left == 0){
            return right + 1;
        }
        if(right == 0) {
            return left + 1;
        }
        return Math.min(left, right) + 1;
    }
}
