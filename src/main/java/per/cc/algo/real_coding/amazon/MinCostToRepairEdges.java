package per.cc.algo.real_coding.amazon;

import java.util.*;

/**
 * https://leetcode.com/discuss/interview-question/357310
 * There's an undirected connected graph with n nodes labeled 1..n. But some of the edges has been broken
 * disconnecting the graph. Find the minimum cost to repair the edges so that all the nodes are once again accessible
 * from each other.
 * <p>
 * Input:
 * <p>
 * n, an int representing the total number of nodes.
 * edges, a list of integer pair representing the nodes connected by an edge.
 * edgesToRepair, a list where each element is a triplet representing the pair of nodes between which an edge is
 * currently broken and the cost of repearing that edge, respectively (e.g. [1, 2, 12] means to repear an edge
 * between nodes 1 and 2, the cost would be 12).
 * Example 1:
 * <p>
 * Input: n = 5, edges = [[1, 2], [2, 3], [3, 4], [4, 5], [1, 5]], edgesToRepair = [[1, 2, 12], [3, 4, 30], [1, 5, 8]]
 * Output: 20
 * Explanation:
 * There are 3 connected components due to broken edges: [1], [2, 3] and [4, 5].
 * We can connect these components into a single component by repearing the edges between nodes 1 and 2, and nodes 1
 * and 5 at a minimum cost 12 + 8 = 20.
 * Example 2:
 * <p>
 * Input: n = 6, edges = [[1, 2], [2, 3], [4, 5], [3, 5], [1, 6], [2, 4]], edgesToRepair = [[1, 6, 410], [2, 4, 800]]
 * Output: 410
 * Example 3:
 * <p>
 * Input: n = 6, edges = [[1, 2], [2, 3], [4, 5], [5, 6], [1, 5], [2, 4], [3, 4]], edgesToRepair = [[1, 5, 110], [2,
 * 4, 84], [3, 4, 79]]
 * Output: 79
 */
public class MinCostToRepairEdges {
    public static void main(String[] args) {
        int res = costToRepair(5, new int[][]{
                {1, 2}, {2, 3}, {3, 4}, {4, 5}, {1, 5}
        }, new int[][]{
                {1, 2, 12}, {3, 4, 30}, {1, 5, 8}
        });
        System.out.println(res);
    }

    private static int costToRepair(int n, int[][] edges, int[][] edgesToRepair) {
        HashMap<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] i : edgesToRepair) {
            if (map.containsKey(i[0])) {
                map.get(i[0]).add(i[1]);
            }else{
                Set<Integer> set = new HashSet<>();
                set.add(i[1]);
                map.put(i[0], set);
            }
        }
        UF uf = new UF(n);
        int cost = 0;
        for (int[] i : edges) {
            if (map.containsKey(i[0]) && map.get(i[0]).contains(i[1])) {
                continue;
            }
            uf.union(i[0] - 1,i[1] - 1);
        }
        Arrays.sort(edgesToRepair, Comparator.comparingInt(a -> a[2]));
        for (int[] i : edgesToRepair) {
            if (uf.connected(i[0] - 1, i[1] - 1)) {
                continue;
            }
            if(uf.count() == 1){
                return cost;
            }
            uf.union(i[0] - 1, i[1] - 1);
            cost += i[2];
        }
        return uf.count() == 1 ? cost : -1;
    }


}

class UF {
    private int[] parent;  // parent[i] = parent of i
    private int count;     // number of components

    public UF(int n) {
        parent = new int[n];
        count = n;
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public int count() {
        return count;
    }

    public int find(int p) {
        while (p != parent[p])
            p = parent[p];
        return p;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;
        parent[rootP] = rootQ;
        count--;
    }


}
