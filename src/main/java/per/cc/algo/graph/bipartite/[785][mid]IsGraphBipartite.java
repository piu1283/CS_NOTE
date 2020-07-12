package per.cc.algo.graph.bipartite;

import java.util.*;

/**
 * Created by Chen on 7/10/20.
 */
class IsGraphBipartite {
    public boolean isBipartite_dfs(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n];
        Arrays.fill(color, -1);

        for (int start = 0; start < n; ++start) {
            if (color[start] == -1) {
                Stack<Integer> stack = new Stack();
                stack.push(start);
                color[start] = 0;

                while (!stack.empty()) {
                    Integer node = stack.pop();
                    for (int nei: graph[node]) {
                        if (color[nei] == -1) {
                            stack.push(nei);
                            color[nei] = color[node] ^ 1;
                        } else if (color[nei] == color[node]) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }
    public boolean isBipartite_bfs(int[][] graph) {
        HashMap<Integer, Boolean> map = new HashMap<>();
        Set<Integer> seen = new HashSet<>();
        ArrayDeque<Integer> que = new ArrayDeque<>();
        for(int j = 0; j < graph.length; j++){
            if (!seen.contains(j)){
                seen.add(j);
                que.addFirst(j);
                map.put(j, true);
            }else{
                continue;
            }
            while(!que.isEmpty()){
                int cur = que.removeFirst();
                // check adjcent node's color
                Boolean curColor = map.get(cur);
                int[] adj = graph[cur];
                for(int i : adj){
                    if (map.containsKey(i)){
                        if(map.get(i) == curColor){
                            return false;
                        }
                    }else{
                        map.put(i, !curColor);
                    }
                    if (!seen.contains(i)){
                        que.add(i);
                        seen.add(i);
                    }
                }
            }
        }

        return true;
    }
}
