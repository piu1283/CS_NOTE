package per.cc.algo.trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Given a 2D board and a list of words from the dictionary, find all words in the board.
 * <p>
 * Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those
 * horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
 * <p>
 * <p>
 * <p>
 * Example:
 * <p>
 * Input:
 * board = [
 * ['o','a','a','n'],
 * ['e','t','a','e'],
 * ['i','h','k','r'],
 * ['i','f','l','v']
 * ]
 * words = ["oath","pea","eat","rain"]
 * <p>
 * Output: ["eat","oath"]
 * <p>
 * <p>
 * Note:
 * <p>
 * All inputs are consist of lowercase letters a-z.
 * The values of words are distinct.
 */
class WordSearchII {
    // this problem combine backtracking and trie, classic!!!!!!!!
    public List<String> findWords(char[][] board, String[] words) {
        // contruct the Trie Tree
        List<String> res = new ArrayList<>();
        TrieNode root = new TrieNode();
        for(String word : words){
            TrieNode n = root;
            for(char c : word.toCharArray()){
                if(n.chMap.containsKey(c)){
                    n = n.chMap.get(c);
                }else{
                    TrieNode t = new TrieNode();
                    n.chMap.put(c, t);
                    n = t;
                }
            }
            n.word = word;
        }
        for (int row = 0; row < board.length; ++row) {
            for (int col = 0; col < board[row].length; ++col) {
                if (root.chMap.containsKey(board[row][col])) {
                    backtracking(board, row, col, root, res);
                }
            }
        }
        return res;
    }

    private void backtracking(char[][] b, int row, int col, TrieNode parent, List<String> res){
        if(row < 0 || row >= b.length || col < 0 || col >= b[row].length || !parent.chMap.containsKey(b[row][col])){
            return;
        }
        char curChar = b[row][col];
        TrieNode curNode = parent.chMap.get(curChar);
        if(curNode.word != null){
            res.add(curNode.word);
            curNode.word = null;
        }
        b[row][col] = '#';
        backtracking(b, row - 1, col, curNode, res);
        backtracking(b, row + 1, col, curNode, res);
        backtracking(b, row, col - 1, curNode, res);
        backtracking(b, row, col + 1, curNode, res);
        b[row][col] = curChar;
        if(curNode.chMap.isEmpty()){
            parent.chMap.remove(curChar);
        }
    }
}

class TrieNode{
    HashMap<Character, TrieNode> chMap = new HashMap<>();
    String word = null;
    public TrieNode(){}
}
