package per.cc.algo.graph.BFS;

import java.util.*;

/**
 * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation
 * sequence from beginWord to endWord, such that:
 *
 * Only one letter can be changed at a time.
 * Each transformed word must exist in the word list.
 * Note:
 *
 * Return 0 if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * You may assume no duplicates in the word list.
 * You may assume beginWord and endWord are non-empty and are not the same.
 * Example 1:
 *
 * Input:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 *
 * Output: 5
 *
 * Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
 * Example 2:
 *
 * Input:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 *
 * Output: 0
 *
 * Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 */
public class BidirectionalBFS {
    // This a example of bidirectional BFS
    // we can run two BFS at the same time, one from the source, another from the target.
    // when the node they search collided, which means the search is finished successfully
    // This method is more efficient in multi-thread env.
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (beginWord == null || endWord == null || beginWord.length() != endWord.length()) {
            return 0;
        }
        Set<String> words = new HashSet<>(wordList);
        if (!words.contains(endWord)) {
            return 0;
        }
        if (beginWord.equals(endWord)) { // crucial for Solution #2
            return 1;
        }

        Deque<String> deque1 = new ArrayDeque<>(); // use as queue
        Deque<String> deque2 = new ArrayDeque<>(); // use as queue

        Set<String> visited1 = new HashSet<>();
        Set<String> visited2 = new HashSet<>();

        deque1.add(beginWord);
        deque2.add(endWord);

        visited1.add(beginWord);
        visited2.add(endWord);

        int distance = 2;

        while (!deque1.isEmpty() || !deque2.isEmpty()) {
            if (checkNeighbors(deque1, visited1, visited2, words)) {
                return distance;
            }
            distance++;
            if (checkNeighbors(deque2, visited2, visited1, words)) {
                return distance;
            }
            distance++;
        }
        return 0; // no transformation sequence exists
    }

    private boolean checkNeighbors(Deque<String> deque,
                                   Set<String> visitedFromThisSide,
                                   Set<String> visitedFromThatSide,
                                   Set<String> words) {
        int wordsInLevel = deque.size();
        for (int i = 0; i < wordsInLevel; i++) {
            String word = deque.removeFirst();
            for (String neighbor : getNeighbors(word, words)) {
                if (visitedFromThatSide.contains(neighbor)) {
                    return true;
                }
                if (!visitedFromThisSide.contains(neighbor)) {
                    visitedFromThisSide.add(neighbor);
                    deque.add(neighbor);
                }
            }
        }
        return false;
    }

    // Generates all possible neighbors of given String
    private Set<String> getNeighbors(String str, Set<String> words) {
        Set<String> validWords = new HashSet<>();
        for (int i = 0; i < str.length(); i++) {
            char[] neighbor = str.toCharArray();
            for (char ch = 'a'; ch <= 'z'; ch++) {
                neighbor[i] = ch;
                String word = new String(neighbor);
                if (words.contains(word)) {
                    validWords.add(word);
                }
            }
        }
        validWords.remove(str); // original String is not its own neighbor
        return validWords;
    }
}
