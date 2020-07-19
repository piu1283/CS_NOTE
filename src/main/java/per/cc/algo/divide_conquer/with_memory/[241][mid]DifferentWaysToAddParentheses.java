package per.cc.algo.divide_conquer.with_memory;

import java.util.*;

/**
 * Given a string of numbers and operators, return all possible results from computing all the different possible
 * ways to group numbers and operators. The valid operators are +, - and *.
 *
 * Example 1:
 *
 * Input: "2-1-1"
 * Output: [0, 2]
 * Explanation:
 * ((2-1)-1) = 0
 * (2-(1-1)) = 2
 * Example 2:
 *
 * Input: "2*3-4*5"
 * Output: [-34, -14, -10, -10, 10]
 * Explanation:
 * (2*(3-(4*5))) = -34
 * ((2*3)-(4*5)) = -14
 * ((2*(3-4))*5) = -10
 * (2*((3-4)*5)) = -10
 * (((2*3)-4)*5) = 10
 */
class DifferentWaysToAddParentheses {
    public List<Integer> diffWaysToCompute(String input) {
        Map<String, List<Integer>> mem = new HashMap<>();
        return helper(input, mem);
    }

    private List<Integer> helper(String input, Map<String, List<Integer>> mem){
        if (mem.containsKey(input)){
            System.out.println("1");
            return mem.get(input);
        }
        List<Integer> curList = new ArrayList<>();
        if(isNumber(input)){
            curList.add(Integer.parseInt(input));
            return curList;
        }
        for(int i = 0; i < input.length(); i++){
            if(!Character.isDigit(input.charAt(i))){
                List<Integer> left = helper(input.substring(0, i), mem);
                List<Integer> right = helper(input.substring(i + 1), mem);
                for(int l : left){
                    for(int r : right){
                        curList.add(calc(l, input.charAt(i) ,r));
                    }
                }
            }
        }
        mem.put(input, curList);
        return curList;
    }

    private int calc(int i, char op, int j){
        switch (op) {
            case '+':
                return i + j;
            case '-':
                return i - j;
            case '*':
                return i * j;
        }
        return 0;
    }

    private boolean isNumber(String s){
        for(int i = 0; i < s.length(); i++){
            if(!Character.isDigit(s.charAt(i))){
                return false;
            }
        }
        return true;
    }
}
