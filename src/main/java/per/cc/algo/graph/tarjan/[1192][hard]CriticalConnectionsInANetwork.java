package per.cc.algo.graph.tarjan;

import java.util.*;

/**
 * There are n servers numbered from 0 to n-1 connected by undirected server-to-server connections forming a network
 * where connections[i] = [a, b] represents a connection between servers a and b. Any server can reach any other
 * server directly or indirectly through the network.
 * <p>
 * A critical connection is a connection that, if removed, will make some server unable to reach some other server.
 * <p>
 * Return all critical connections in the network in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * <p>
 * Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
 * Output: [[1,3]]
 * Explanation: [[3,1]] is also accepted.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 10^5
 * n-1 <= connections.length <= 10^5
 * connections[i][0] != connections[i][1]
 * There are no repeated connections.
 */
class CriticalConnectionsInANetwork {
    List<List<Integer>> ans = new ArrayList<>();
    Map<Integer,List<Integer>> map = new HashMap<>();

    int curdepth=0;

    int[] low;
    int[] label;

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        // build graph
        low = new int[n];
        label = new int[n];


        for(List<Integer> con: connections){
            int i = con.get(0);
            int j = con.get(1);
            if(map.containsKey(i)){
                map.get(i).add(j);
            }else{
                ArrayList<Integer> l = new ArrayList<>();
                l.add(j);
                map.put(i, l);
            }
            if(map.containsKey(j)){
                map.get(j).add(i);
            }else{
                ArrayList<Integer> l = new ArrayList<>();
                l.add(i);
                map.put(j, l);
            }
        }

        boolean [] visited = new boolean[n];
        dfs(visited, 0, -1);

        return ans;
    }

    private void dfs(boolean[] visited, int curNode, int parentNode){
        low[curNode] = curdepth;   // assign low for curNode
        visited[curNode] = true;
        label[curNode] = curdepth; // assign label for curNode
        curdepth++;

        for(int toNode: map.get(curNode)){
            if(toNode == parentNode) continue;

            if(!visited[toNode]){
                dfs(visited, toNode, curNode);

                low[curNode] = Math.min(low[curNode], low[toNode]);
                // now, if there is no cycle - toNode's low will be higher than curNodes label
                if(label[curNode] < low[toNode]){
                    ans.add(Arrays.asList(curNode, toNode));
                }
            }else { 
                // cycle found - carry over the low
                // also not critical path
                int newlow = Math.min(low[curNode], low[toNode]);
                low[curNode] = newlow;
            }
        }
    }
}
