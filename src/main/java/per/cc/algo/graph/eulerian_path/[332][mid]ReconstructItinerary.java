package per.cc.algo.graph.eulerian_path;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the
 * itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.
 * <p>
 * Note:
 * <p>
 * If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when
 * read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
 * All airports are represented by three capital letters (IATA code).
 * You may assume all tickets form at least one valid itinerary.
 * One must use all the tickets once and only once.
 * Example 1:
 * <p>
 * Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 * Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]
 * Example 2:
 * <p>
 * Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
 * Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
 * But it is larger in lexical order.
 */
class ReconstructItinerary {
    HashMap<String, LinkedList<String>> graph = new HashMap<>();
    LinkedList<String> result = null;
    public List<String> findItinerary(List<List<String>> tickets) {
        // build the graph
        for(List<String> ti : tickets){
            String from = ti.get(0);
            String to = ti.get(1);
            if(graph.containsKey(from)){
                graph.get(from).add(to);
            }else{
                LinkedList<String> t = new LinkedList<>();
                t.add(to);
                graph.put(from, t);
            }
        }
        graph.forEach((k,v)-> Collections.sort(v));
        result = new LinkedList<>();
        dfs("JFK");
        return result;
    }

    private void dfs(String start){
        if(graph.containsKey(start)){
            LinkedList<String> dt = graph.get(start);
            while(!dt.isEmpty()){
                String to = dt.pollFirst();
                dfs(to);
            }
        }
        result.addFirst(start);
    }
}
