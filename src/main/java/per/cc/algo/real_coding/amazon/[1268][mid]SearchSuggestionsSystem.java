package per.cc.algo.real_coding.amazon;

import java.util.*;

/**
 * Given an array of strings products and a string searchWord. We want to design a system that suggests at most three
 * product names from products after each character of searchWord is typed. Suggested products should have common
 * prefix with the searchWord. If there are more than three products with a common prefix return the three
 * lexicographically minimums products.
 * <p>
 * Return list of lists of the suggested products after each character of searchWord is typed.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: products = ["mobile","mouse","moneypot","monitor","mousepad"], searchWord = "mouse"
 * Output: [
 * ["mobile","moneypot","monitor"],
 * ["mobile","moneypot","monitor"],
 * ["mouse","mousepad"],
 * ["mouse","mousepad"],
 * ["mouse","mousepad"]
 * ]
 * Explanation: products sorted lexicographically = ["mobile","moneypot","monitor","mouse","mousepad"]
 * After typing m and mo all products match and we show user ["mobile","moneypot","monitor"]
 * After typing mou, mous and mouse the system suggests ["mouse","mousepad"]
 * Example 2:
 * <p>
 * Input: products = ["havana"], searchWord = "havana"
 * Output: [["havana"],["havana"],["havana"],["havana"],["havana"],["havana"]]
 * Example 3:
 * <p>
 * Input: products = ["bags","baggage","banner","box","cloths"], searchWord = "bags"
 * Output: [["baggage","bags","banner"],["baggage","bags","banner"],["baggage","bags"],["bags"]]
 * Example 4:
 * <p>
 * Input: products = ["havana"], searchWord = "tatiana"
 * Output: [[],[],[],[],[],[],[]]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= products.length <= 1000
 * There are no repeated elements in products.
 * 1 <= Σ products[i].length <= 2 * 10^4
 * All characters of products[i] are lower-case English letters.
 * 1 <= searchWord.length <= 1000
 * All characters of searchWord are lower-case English letters.
 */
class SearchSuggestionsSystem {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        // build trieNode of SearchWord
        TrieNode root = new TrieNode();
        TrieNode cur = root;
        int pos = 0;
        while(pos < searchWord.length()){
            TrieNode tmp = new TrieNode();
            cur.ch[searchWord.charAt(pos) - 'a'] = tmp;
            cur = cur.ch[searchWord.charAt(pos) - 'a'];
            pos++;
        }
        Arrays.sort(products);
        List<List<String>> res = new ArrayList<>(searchWord.length());
        for(int i = 0; i < searchWord.length(); i++){
            res.add(new ArrayList<>(3));
        }
        for(String s : products){
            findAndPut(res, s, root);
        }
        return res;
    }

    private void findAndPut(List<List<String>> res, String s, TrieNode root){
        TrieNode cur = root;
        int i = 0;
        while(i < s.length()){
            if(cur.ch[s.charAt(i) - 'a'] != null){
                cur = cur.ch[s.charAt(i) - 'a'];
                if(res.get(i).size() < 3){
                    res.get(i).add(s);
                }
            }else{
                break;
            }
            i++;
        }
    }

    class TrieNode{
        TrieNode[] ch = new TrieNode[26];
    }
}
