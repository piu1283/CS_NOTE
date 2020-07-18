package per.cc.algo.string.palindrome;

import java.util.*;

/**
 * Given a string s, return all the palindromic permutations (without duplicates) of it. Return an empty list if no
 * palindromic permutation could be form.
 *
 * Example 1:
 *
 * Input: "aabb"
 * Output: ["abba", "baab"]
 *
 * Example 2:
 *
 * Input: "abc"
 * Output: []
 */
class PalindromePermutation {
    // this problem is a classic backtracking
    public List<String> generatePalindromes(String s) {
        Set<String> set = new HashSet<>();
        HashMap<Character, Integer> map = new HashMap<>();
        for(char c : s.toCharArray()){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        // check
        int numOfOdd = 0;
        for(int i : map.values()){
            if ((i & 1) == 1){
                numOfOdd++;
            }
            if(numOfOdd > 1){
                return new ArrayList<>();
            }
        }
        helper(map, set, new char[s.length()], 0, s.length() - 1);
        return new ArrayList<>(set);
    }

    // to save space, we can use a char arr to store the permutation.
    private void helper(HashMap<Character, Integer> map, Set<String> res, char[] arr, int l, int r){
        if (map.size() == 0){
            res.add(String.valueOf(arr));
        }
        List<Character> list = new ArrayList<>(map.keySet());
        for(char c : list){
            if(l == r){
                arr[l] = c;
                map.remove(c);
                helper(map, res, arr, l + 1, r - 1);
                map.put(c, 1);
            }
            if(map.get(c) > 1){
                arr[r] = c;
                arr[l] = c;
                if(map.get(c) > 2){
                    map.put(c, map.get(c) - 2);
                }else{
                    map.remove(c);
                }
                helper(map, res, arr, l + 1, r - 1);
                map.put(c, map.getOrDefault(c, 0) + 2);
            }
        }
    }
}
