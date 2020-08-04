package per.cc.algo.trie;

import java.util.*;

/**
 * Given a list of words (without duplicates), please write a program that returns all concatenated words in the
 * given list of words.
 * A concatenated word is defined as a string that is comprised entirely of at least two shorter words in the given
 * array.
 * <p>
 * Example:
 * Input: ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
 * <p>
 * Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]
 * <p>
 * Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats";
 * "dogcatsdog" can be concatenated by "dog", "cats" and "dog";
 * "ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".
 * Note:
 * The number of elements of the given array will not exceed 10,000
 * The length sum of elements in the given array will not exceed 600,000.
 * All the input string will only include lower case letters.
 * The returned elements order does not matter.
 */
class ConcatenatedWordsDP {
    // using dp
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Set<String> set = new HashSet<>();
        for(String s : words){
            if(s.equals("")) continue;
            set.add(s);
        }
        List<String> res = new ArrayList<>();
        for(int i = 0; i < words.length;i++){
            if(words[i].equals("")) continue;
            set.remove(words[i]);
            if(isValid(words[i], set)){
                res.add(words[i]);
            }
            set.add(words[i]);
        }
        return res;
    }

    private boolean isValid(String s, Set<String> set){
        if(set.isEmpty()){
            return false;
        }
        boolean [] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for(int i = 1; i <= s.length(); i++){
            for(int j = 0; j <= i; j++){
                if(dp[j] && (j == i || set.contains(s.substring(j, i)))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}

class ConcatenatedWordsDPTIRE {

    // using tire and DFS
    public static List<String> findAllConcatenatedWordsInADict(String[] words) {
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        List<String> concatenated = new ArrayList<>();
        for (String word : words) {
            if (isConcatenated(trie, word, 0) > 1) {
                concatenated.add(word);
            }
        }

        return concatenated;
    }

    private static int isConcatenated(Trie trie, String word, int i) {
        if (i == word.length()) {
            return 0;
        }

        Node node = trie.head;
        while (i < word.length()) {
            node = node.getChildAt(word.charAt(i++));
            if (node == null) {
                return 0;
            } else if (node.terminal) {
                int subCount = isConcatenated(trie, word, i);
                if (subCount > 0) {
                    return subCount + 1;
                }
            }
        }

        return node.terminal ? 1 : 0;
    }

    private static class Trie {

        static final int CHAR_COUNT = 'z' - 'a' + 1;

        final Node head = new Node(false, new Node[CHAR_COUNT]);

        Trie() {
        }

        void insert(String word) {
            Node node = head;
            int i = 0;
            while (i < word.length()) {
                char c = word.charAt(i++);
                node = node.getOrCreateChildAt(c);
            }

            node.terminal = true;
        }
    }

    private static class Node {
        boolean terminal;
        Node[] children;

        Node(boolean terminal, Node[] children) {
            this.terminal = terminal;
            this.children = children;
        }

        Node getChildAt(char c) {
            return children[index(c)];
        }

        Node getOrCreateChildAt(char c) {
            int i = index(c);
            Node child = children[i];
            if (child == null) {
                child = new Node(false, new Node[Trie.CHAR_COUNT]);
                children[i] = child;
            }

            return child;
        }

        int index(char c) {
            return c - 'a';
        }
    }
}
