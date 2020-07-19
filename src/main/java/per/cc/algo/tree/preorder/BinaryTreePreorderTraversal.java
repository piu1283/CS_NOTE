package per.cc.algo.tree.preorder;

import per.cc.util.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary tree, return the preorder traversal of its nodes' values.
 * <p>
 * Example:
 * <p>
 * Input: [1,null,2,3]
 * 1
 * \
 * 2
 * /
 * 3
 * <p>
 * Output: [1,2,3]
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 */
class BinaryTreePreorderTraversal {
    List<Integer> res = new ArrayList();

    public List<Integer> preorderTraversal(TreeNode root) {
        if (root != null) {
            normalWay(root);
        }
        return res;
    }

    public List<Integer> normalWay(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode tmp = stack.pop();
            if (tmp.right != null) {
                stack.push(tmp.right);
            }
            if (tmp.left != null) {
                stack.push(tmp.left);
            }
            res.add(tmp.val);
        }
        return res;
    }

    public List<Integer> recurenceWay(TreeNode root) {
        if (root != null) {
            res.add(root.val);
            preorderTraversal(root.left);
            preorderTraversal(root.right);
        }
        return res;
    }
}
