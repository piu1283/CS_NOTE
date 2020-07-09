package per.cc.algo.shortestpath;

import java.util.*;

/**
 * Created by Chen on 3/15/20.
 */
public class Dijkstra {
    public static void findPath(List<List<Node>> graph, int[] dist, int V, int[] path, int src) {
        Set<Integer> visited = new HashSet<>();
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[src] = 0;
        pq.add(new Node(src, 0));
        while (visited.size() < V) {
            if (pq.isEmpty()) {
                return;
            }
            Node n = pq.remove();
            if (visited.contains(n.idx)) {
                continue;
            }
            visited.add(n.idx);
            // process its adjs
            List<Node> adjs = graph.get(n.idx);
            for (Node node : adjs) {
                if (!visited.contains(node.idx)) {
                    if (dist[n.idx] + node.cost < dist[node.idx]) {
                        dist[node.idx] = dist[n.idx] + node.cost;
                        path[node.idx] = n.idx;
                    }
                    pq.add(node);
                }
            }
        }
    }

    static class Node implements Comparable<Node>{
        int cost;
        Integer idx;

        public Node() {
        }

        public Node(int idx,int cost) {
            this.cost = cost;
            this.idx = idx;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) {
        List<List<Node> > adj = new ArrayList<List<Node>>();
        int V = 5;
        int src = 0;
        // Initialize list for every node
        for (int i = 0; i < V; i++) {
            List<Node> item = new ArrayList<Node>();
            adj.add(item);
        }

        // Inputs for the DPQ graph
        adj.get(0).add(new Node(1, 9));
        adj.get(0).add(new Node(2, 6));
        adj.get(0).add(new Node(3, 5));
        adj.get(0).add(new Node(4, 3));

        adj.get(2).add(new Node(1, 2));
        adj.get(2).add(new Node(3, 4));
        int[] dist = new int[V];
        int[] path = new int[V];
        findPath(adj, dist, V, path, src);
        System.out.println("The shorted path from node :");
        for (int i = 0; i < dist.length; i++)
            System.out.println(src + " to " + i + " is "
                    + dist[i] + " path :" + path[i]);
    }
}
