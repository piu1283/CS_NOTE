package per.cc.algo.tree.levelorder;

import per.cc.util.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
 * <p>
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * return its level order traversal as:
 * [
 * [3],
 * [9,20],
 * [15,7]
 * ]
 */
public class BinaryTreeLevelOrderTraversal {
    public List<List<Integer>> levelOrder_iterative(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        ArrayDeque<TreeNode> que = new ArrayDeque<>();
        que.addLast(root);
        while (!que.isEmpty()) {
            int size = que.size();
            List<Integer> levelRes = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode tmp = que.removeFirst();
                levelRes.add(tmp.val);
                if (tmp.left != null) {
                    que.add(tmp.left);
                }
                if (tmp.right != null) {
                    que.add(tmp.right);
                }
            }
            res.add(levelRes);
        }
        return res;
    }

    public List<List<Integer>> levelOrder_recursive(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        helper(res, 0, root);
        return res;
    }

    private void helper(List<List<Integer>> res, int level, TreeNode root) {
        if (root != null) {
            if (res.size() > level) {
                res.get(level).add(root.val);
            } else {
                List<Integer> t = new ArrayList<>();
                t.add(root.val);
                res.add(t);
            }
            helper(res, level + 1, root.left);
            helper(res, level + 1, root.right);
        }
    }
}
