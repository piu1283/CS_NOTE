package per.cc.algo.graph.MST.primes;

import java.util.*;

/**
 * There are N cities numbered from 1 to N.
 * <p>
 * You are given connections, where each connections[i] = [city1, city2, cost] represents the cost to connect city1
 * and city2 together.  (A connection is bidirectional: connecting city1 and city2 is the same as connecting city2
 * and city1.)
 * <p>
 * Return the minimum cost so that for every pair of cities, there exists a path of connections (possibly of length
 * 1) that connects those two cities together.  The cost is the sum of the connection costs used. If the task is
 * impossible, return -1.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * <p>
 * Input: N = 3, connections = [[1,2,5],[1,3,6],[2,3,1]]
 * Output: 6
 * Explanation:
 * Choosing any 2 edges will connect all cities so we choose the minimum 2.
 * Example 2:
 * <p>
 * <p>
 * <p>
 * Input: N = 4, connections = [[1,2,3],[3,4,4]]
 * Output: -1
 * Explanation:
 * There is no way to connect all cities even if all edges are used.
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= N <= 10000
 * 1 <= connections.length <= 10000
 * 1 <= connections[i][0], connections[i][1] <= N
 * 0 <= connections[i][2] <= 10^5
 * connections[i][0] != connections[i][1]
 */
class ConnectingCitiesWithMinimumCost {
    public int minimumCost(int N, int[][] connections) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->{
            return a[1] - b[1];
        });
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for(int i = 1; i <= N; i++){
            graph.put(i, new ArrayList<>());
        }
        for(int[] edge : connections){
            graph.get(edge[0]).add(new int[]{edge[1],edge[2]});
            graph.get(edge[1]).add(new int[]{edge[0],edge[2]});
        }
        for(List<int[]> conn : graph.values()){
            if(conn.size() < 1){
                return -1;
            }
        }
        pq.add(new int[]{1,0});
        int cost = 0;
        int cities = 0;
        boolean[] seen = new boolean[N + 1];
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            if(seen[cur[0]]) continue;
            cost += cur[1];
            cities++;
            seen[cur[0]] = true;
            for(int[] adj : graph.get(cur[0])){
                pq.add(adj);
            }
            if(cities == N) return cost;
        }
        if(cities != N){
            return -1;
        }
        return cost;
    }
}
