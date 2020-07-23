package per.cc.algo.tree.buttom_top;

import per.cc.util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until
 * the tree is empty.
 *
 *
 *
 * Example:
 *
 * Input: [1,2,3,4,5]
 *
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 *
 * Output: [[4,5,3],[2],[1]]
 *
 *
 * Explanation:
 *
 * 1. Removing the leaves [4,5,3] would result in this tree:
 *
 *           1
 *          /
 *         2
 *
 *
 * 2. Now removing the leaf [2] would result in this tree:
 *
 *           1
 *
 *
 * 3. Now removing the leaf [1] would result in the empty tree:
 *
 *           []
 * [[3,5,4],[2],[1]], [[3,4,5],[2],[1]], etc, are also consider correct answers since per each level it doesn't
 * matter the order on which elements are returned.
 */
class FindLeavesOfBinaryTree {
    // the naive way, top-buttom, every time just remove the leaves and stored in a list
    // time compcity: O(H^2)
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root  == null){
            return res;
        }
        while(true){
            List<Integer> tmp = new ArrayList<>();
            if(root.left == null && root.right == null){
                tmp.add(root.val);
                res.add(tmp);
                break;
            }
            helper(root, null, true, tmp);
            res.add(tmp);

        }
        return res;
    }

    private void helper(TreeNode root, TreeNode parent, boolean left, List<Integer> tmp){
        if(root.left == null && root.right == null){
            if(parent != null){
                if(left){
                    parent.left = null;
                }else{
                    parent.right = null;
                }
            }
            tmp.add(root.val);
            return;
        }
        if(root.left != null){
            helper(root.left, root, true, tmp);
        }
        if(root.right != null){
            helper(root.right, root, false, tmp);
        }
    }
}

class FindLeavesOfBinaryTree2{
    // buttom-top O(n)
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root  == null){
            return res;
        }
        hight(root, res);
        return res;
    }

    private int hight(TreeNode root, List<List<Integer>> res){
        if(root == null){
            return -1;
        }
        int h = 1 + Math.max(hight(root.left, res), hight(root.right, res));
        if(res.size() <= h){
            List<Integer> tmp = new ArrayList<>();
            tmp.add(root.val);
            res.add(tmp);
        }else{
            res.get(h).add(root.val);
        }
        return h;
    }
}
