package per.cc.algo.tree.levelorder;

import per.cc.util.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 *
 * For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 *
 *
 * But the following [1,2,2,null,3,null,3] is not:
 *
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 *
 *
 * Follow up: Solve it both recursively and iteratively.
 */
class SymmetricTree {
    public boolean isSymmetric_recursive(TreeNode root) {
        return isMirror(root, root);
    }

    private boolean isMirror(TreeNode n1, TreeNode n2){
        if(n1 == null && n2 == null){
            return true;
        }
        if (n1 == null || n2 == null){
            return false;
        }
        return n1.val == n2.val && isMirror(n1.left, n2.right) && isMirror(n1.right, n2.left);
    }

    public boolean isSymmetric_iterative(TreeNode root) {
        if(root == null){
            return true;
        }
        ArrayDeque<TreeNode> que = new ArrayDeque<>();
        que.addLast(root);
        while(!que.isEmpty()){
            int size = que.size();
            List<Integer> line = new ArrayList<>();
            for(int i = 0; i < size; i++){
                TreeNode tmp = que.removeFirst();
                if(tmp.val == Integer.MIN_VALUE){
                    line.add(Integer.MIN_VALUE);
                }else{
                    line.add(tmp.val);
                    que.addLast(tmp.left == null ? new TreeNode(Integer.MIN_VALUE) : tmp.left);
                    que.addLast(tmp.right == null ? new TreeNode(Integer.MIN_VALUE) : tmp.right);
                }
            }
            if(!isSy(line)){
                return false;
            }
        }
        return true;
    }

    private boolean isSy(List<Integer> list){
        int l = 0;
        int r = list.size() - 1;
        while(l < r){
            if(list.get(l).compareTo(list.get(r)) != 0){
                return false;
            }
            l++;
            r--;
        }
        return true;
    }
}
