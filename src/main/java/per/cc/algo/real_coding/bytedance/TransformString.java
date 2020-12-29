package per.cc.algo.real_coding.bytedance;

import java.util.*;

public class TransformString {
    public static void main(String[] args) {
        System.out.println(transform("aaa", "bbb"));
        System.out.println(transform("ababcc", "cccccc"));
        System.out.println(transform("ab", "ba"));
        System.out.println(transform("abac", "wxyz"));
        System.out.println(transform("aabcc", "ccdee"));
        System.out.println(transform("abcdefghijklmnopqrstuvwxyz", "bcdefghijklmnopqrstuvwxyzq"));
        System.out.println(transform("abcdefghijklmnopqrstuvwxy", "bcdefghijklmnopqrstuvwxya"));
    }
    public static int transform(String source, String target) {
        // Write your code here
        Map<Integer, Integer> map = new HashMap<>();
        HashSet<Integer> set = new HashSet<>();
        int [] arr = new int[26];
        int cnt = 0;
        for(int i = 0;i < source.length(); i++){
            int c1 = source.charAt(i) - 'a';
            int c2 = target.charAt(i) - 'a';
            if(c1 == c2) continue;
            if(map.containsKey(c1) && map.get(c1) != c2) return -1;
            if(set.contains(c1) && map.containsKey(c2) && arr[c2] == 0) cnt++;
            map.put(c1,c2);
            arr[c2]++;
            set.add(c2);
        }
        return map.size() + cnt;
    }

//    private static int transform(String source, String target) {
//        if (source.equals(target)) return 0;
//        Map<Character, Character> graph = new HashMap<>();
//        for (int i = 0; i < source.length(); ++i) {
//            if (graph.getOrDefault(source.charAt(i), target.charAt(i)) != target.charAt(i))
//                return -1;
//            graph.put(source.charAt(i), target.charAt(i));
//        }
//        if (new HashSet<Character>(graph.values()).size() >= 26) {
//            return -1;
//        }
//        int totalSize = graph.size();
//        // topological sort
//        int[] inDegree = new int[26];
//        for (char c : graph.values()) {
//            inDegree[c - 'a']++;
//        }
//        ArrayDeque<Integer> que = new ArrayDeque<>();
//        for (int i = 0; i < inDegree.length; i++) {
//            if (inDegree[i] < 1 && graph.containsKey((char) (i + 'a'))) {
//                que.push(i);
//            }
//        }
//        while (!que.isEmpty()) {
//            int size = que.size();
//            for (int i = 0; i < size; i++) {
//                int tmp = que.poll();
//                char tmpChar = (char) (tmp + 'a');
//                if (graph.containsKey(tmpChar)) {
//                    int toIndex = graph.get(tmpChar) - 'a';
//                    if (--inDegree[toIndex] == 0) {
//                        que.add(toIndex);
//                    }
//                }
//                graph.remove(tmpChar);
//            }
//        }
//        int remainNum = 0;
//        for (int value : inDegree) {
//            if (value > 0) {
//                remainNum++;
//            }
//        }
//        return (totalSize - remainNum) + remainNum == 0 ? 0 : remainNum + 1;
//    }
}

