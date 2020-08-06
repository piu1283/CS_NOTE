package per.cc.algo.tree.buttom_top;

import per.cc.util.TreeNode;

/**
 * Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), where largest means subtree
 * with largest number of nodes in it.
 * <p>
 * Note:
 * A subtree must include all of its descendants.
 * <p>
 * Example:
 * <p>
 * Input: [10,5,15,1,8,null,7]
 * <p>
 * 10
 * / \
 * 5  15
 * / \   \
 * 1   8   7
 * <p>
 * Output: 3
 * Explanation: The Largest BST Subtree in this case is the highlighted one.
 * The return value is the subtree's size, which is 3.
 * Follow up:
 * Can you figure out ways to solve it with O(n) time complexity?
 */
class LargestBSTSubtree {
    class Info{
        Integer min;
        Integer max;
        boolean isBST = true;
        int num = 0;
        Info(Integer min, Integer max, boolean isBST, int num){
            this.min = min;
            this.max = max;
            this.isBST = isBST;
            this.num = num;
        }
    }

    public int largestBSTSubtree(TreeNode root) {
        if(root == null) {
            return 0;
        }
        helper(root);
        return maxNode;

    }
    int maxNode = 1;
    private Info helper(TreeNode root){
        if(root == null){
            return new Info(null, null, true, 0);
        }
        Info lInfo = helper(root.left);
        Info rInfo = helper(root.right);
        if(!lInfo.isBST || !rInfo.isBST){
            return new Info(lInfo.min, rInfo.max, false, 1);
        }
        int retMin = lInfo.min == null ? root.val : lInfo.min;
        int retMax = rInfo.max == null ? root.val : rInfo.max;
        boolean valid = true;
        if(lInfo.max != null){
            valid = (valid && (root.val > lInfo.max));
        }
        if(rInfo.min != null){
            valid = (valid && (root.val < rInfo.min));
        }
        if(valid){
            maxNode = Math.max(maxNode, lInfo.num + rInfo.num + 1);
            return new Info(retMin, retMax, true, lInfo.num + rInfo.num + 1);
        }else{
            return new Info(retMin, retMax, false, 1);
        }
    }
}
