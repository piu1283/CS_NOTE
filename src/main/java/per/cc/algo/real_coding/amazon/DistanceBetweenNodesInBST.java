package per.cc.algo.real_coding.amazon;

import per.cc.util.TreeNode;

/**
 * https://leetcode.com/discuss/interview-question/376375/
 * Given a list of unique integers nums, construct a BST from it (you need to insert nodes one-by-one with the given
 * order to get the BST) and find the distance between two nodes node1 and node2. Distance is the number of edges
 * between two nodes. If any of the given nodes does not appear in the BST, return -1.
 *
 * Example 1:
 *
 * Input: nums = [2, 1, 3], node1 = 1, node2 = 3
 * Output: 2
 * Explanation:
 *      2
 *    /   \
 *   1     3
 */
class DistanceBetweenNodesInBST {

    public static void main(String[] args) {
        DistanceBetweenNodesInBST dis = new DistanceBetweenNodesInBST();
        System.out.println(dis.algo(new int[]{2, 1, 3}, 2, 3));
    }

    private int algo(int[] arr, int x, int y){
        TreeNode root = constructBST(arr, x, y);
        if (root == null) {
            return -1;
        }
        TreeNode lcaNode = lca(root, x, y);
        int dis1 = findDistance(lcaNode, x);
        int dis2 = findDistance(lcaNode, y);
        return dis1 + dis2;
    }

    private int findDistance(TreeNode root, int x) {
        int dis = 0;
        TreeNode cur = root;
        while(cur.val != x){
            if(cur.val < x){
                cur = cur.right;
            }else{
                cur = cur.left;
            }
            dis++;
        }
        return dis;
    }


    private TreeNode lca(TreeNode root, int x, int y){
        if(root == null){
            return null;
        }
        if(root.val == x || root.val == y){
            return root;
        }
        TreeNode left = lca(root.left, x, y);
        TreeNode right = lca(root.right, x, y);
        if(left != null && right != null){
            return root;
        }else if(left == null){
            return right;
        }else{
            return left;
        }
    }


    private TreeNode constructBST(int[] arr, int x, int y){
        boolean findX = false;
        boolean findY = false;
        if(arr.length < 1){
            return null;
        }
        TreeNode root = new TreeNode(arr[0]);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == x) findX = true;
            if (arr[i] == y) findY = true;
            if(i == 0) continue;
            insertBST(root, arr[i]);
        }
        return findX && findY ? root : null;
    }

    private void insertBST(TreeNode root, int val){
        while(root != null){
            if(root.val < val){
                if(root.right != null)
                    root = root.right;
                else{
                    root.right = new TreeNode(val);
                    return;
                }
            }else{
                if(root.left != null)
                    root = root.left;
                else{
                    root.left = new TreeNode(val);
                    return;
                }
            }
        }
    }
}
