package per.cc.algo.real_coding.amazon;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Your team at amazon is overseeing the design of a new high-efficiency data center at HQ2. A power grid need to be
 * generated for supplying power to N servers. All servers in the grid have to be connected such that they have
 * access to power. The cost of connections between different servers varies.
 *
 * Assume that there are no ties, names of servers are unique, connections are directionless, there is at most one
 * connection between a pair of servers, all costs are greater than zero, and a server does not connect to itself.
 *
 * Write an algorithm to minimize the cost of connecting all servers in the power grid.
 *
 * Input
 * two arguments - num, an Integer representing number of connections.
 * connectons, representing a list of connections where each element of the list consists of two servers and the cost
 * of connection between the servers.
 *
 * Note
 * The cost of connection between the servers is always greater than 0.
 *
 * Example
 * Input
 * num = 5
 *
 * connection =
 *  	 [[A,B,1],
 *  	 [B,C,4],
 *  	 [B,D,6],
 *  	 [D,E,5],
 *  	 [C,E,1]]
 * Output
 *
 * [[A,B,1],
 * [B,C,4],
 * [C,E,1],
 * [D,E,5]]
 */
public class PowerGrid {
    public static void main(String[] args) {

    }

//    private static List<List<Integer>> calc(int[][] graph, int n){
//        Map<Integer, List<int[]>> map = new HashMap<>();
//        for (int[] i : graph) {
//
//        }
//
//    }
}
