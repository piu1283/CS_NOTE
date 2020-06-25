package per.cc.tmplate.backtracking.game;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/flip-game-ii/
 * You are playing the following Flip Game with your friend: Given a string that contains only these two characters:
 * + and -, you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no
 * longer make a move and therefore the other person will be the winner.
 *
 * Write a function to determine if the starting player can guarantee a win.
 *
 * Example:
 *
 * Input: s = "++++"
 * Output: true
 * Explanation: The starting player can guarantee a win by flipping the middle "++" to become "+--+".
 * Follow up:
 * Derive your algorithm's runtime complexity.
 */
class FlipGameII {
    public boolean canWin(String s) {
        if(s.equals("")){
            return false;
        }
        char [] arr = s.toCharArray();
        HashMap<String, Boolean> mem = new HashMap<>();
        return helper(arr, mem);
    }

    private boolean helper(char[] arr, HashMap<String, Boolean> mem){
        String s = new String(arr);
        if(mem.containsKey(s)){
            return mem.get(s);
        }
        for(int i = 0; i < arr.length - 1; i++){
            if (arr[i] == '+' && arr[i] == arr[i + 1]){
                arr[i] = '-';
                arr[i + 1] = '-';
                boolean res = helper(arr, mem);
                arr[i] = '+';
                arr[i + 1] = '+';
                if (!res){
                    mem.put(s, true);
                    return true;
                }
            }
        }
        mem.put(s,false);
        return false;
    }
}
