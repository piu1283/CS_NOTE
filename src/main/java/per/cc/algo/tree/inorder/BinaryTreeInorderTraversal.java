package per.cc.algo.tree.inorder;

import per.cc.util.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary tree, return the inorder traversal of its nodes' values.
 *
 * Example:
 *
 * Input: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * Output: [1,3,2]
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 */
public class BinaryTreeInorderTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root == null){
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode cur = root.left;
        while(!stack.isEmpty() || cur != null){
            while(cur != null){
                stack.push(cur);
                cur = cur.left;
            }
            TreeNode tn = stack.pop();
            list.add(tn.val);
            cur = tn.right;
        }
        return list;
    }
}
