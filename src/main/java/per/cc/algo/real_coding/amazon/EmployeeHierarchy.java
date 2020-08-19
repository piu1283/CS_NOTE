package per.cc.algo.real_coding.amazon;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Imagine that an employment tree represents the formal employee hierarchy at Amazon. Manager nodes have
 * chid nodes for each employee that reports to them; each of these employees can, in turn, have child nodes
 * representing their respective reportees. Each node in the tree contains an integer representing the number of
 * months the employee has spent at the company. Team tenure is computed as the average tenure of the manager
 * and all the company employees working below the manager. The oldest team has the highest team tenure.
 *
 * Write an algorithm to find the manager of the team with the highest tenure. An employee must have child nodes
 * to be a manager.
 *
 * Input
 * The input to the function/method consists of an argument -
 * president, a node representing the root node of the employee hierarchy.
 *
 * Output
 * Return the node which has the oldest team.
 *
 * Note
 * There will be at least one child node in the tree and there will be no ties.
 *
 * Example
 *
 * Input
 *    President =
 * 	             20
 *           12            18
 *
 *       11   2   3      15   8
 *
 * Output = 18
 * Explanation :
 * There are three managers in this tree with the following team tenures :
 * 12 => (11+2+3+12)/4 = 7
 * 18 => (18+15+8)/2 = 13.67
 * 20 => (12+11+2+3+18+15+8+20)/8 = 11.125
 */
public class EmployeeHierarchy {
    public static void main(String[] args) {

    }

    private static int calc(Node root){
        rec(root);
        return id;
    }

    static double max = Double.MIN_VALUE;
    static int id = 0;
    // i[0]: sum ; i[1]: cnt
    private static int[] rec(Node root){
        if(root == null){
            return new int[]{0,0};
        }
        List<Node> ch = root.children;
        int sum = 0;
        int cnt = 0;

        for (Node n : ch) {
            int[] tmp = rec(n);
            cnt += tmp[1];
            sum += tmp[0];
        }
        double avg = (sum + root.len) * 1.0 / (cnt + 1);
        if(avg > max){
            max = avg;
            id = root.len;
        }
        return new int[]{sum + root.len, cnt + 1};
    }

    class Node {
        int len;
        List<Node> children = new ArrayList<>();
        public Node(int len){
            this.len = len;
        }
    }
}
