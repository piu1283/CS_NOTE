package per.cc.algo.node_distance_in_cycle;

/**
 * The situation is that, we need to the distance between two node, the node are labeled from 0-9, and place like a ring.
 * 0,1,2,3,4,5,6,7,8,9   So the next node of 9 will be 0, the distance between 9 and 0 is 1
 * We want to calculate the distance between any two node.
 */
public class FindDistance {
    public static void main(String[] args) {
        int node0 = 0;
        int node9 = 9;
        int allNum = 10;
        // !!!!!!!!!!!!!!!
        int distance = ((node0 - node9) + allNum) % allNum;
    }
}
