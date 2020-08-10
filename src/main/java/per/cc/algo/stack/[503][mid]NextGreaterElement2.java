package per.cc.algo.stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * Given a circular array (the next element of the last element is the first element of the array), print the Next
 * Greater Number for every element. The Next Greater Number of a number x is the first greater number to its
 * traversing-order next in the array, which means you could search circularly to find its next greater number. If it
 * doesn't exist, output -1 for this number.
 * <p>
 * Example 1:
 * Input: [1,2,1]
 * Output: [2,-1,2]
 * Explanation: The first 1's next greater number is 2;
 * The number 2 can't find next greater number;
 * The second 1's next greater number needs to search circularly, which is also 2.
 * Note: The length of given array won't exceed 10000.
 */
class NextGreaterElement2 {
    public int[] nextGreaterElements(int[] nums) {
        if(nums.length < 1){
            return new int[0];
        }
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[nums.length];
        Arrays.fill(res, -1);
        for(int k = 0; k < 2; k++){
            for(int i = nums.length - 1; i >= 0; i--){
                if(stack.isEmpty()){
                    stack.push(nums[i]);
                    continue;
                }
                while(!stack.isEmpty() && stack.peek() <= nums[i]){
                    stack.pop();
                }

                if(!stack.isEmpty() && res[i] < 0) res[i] = stack.peek();

                stack.push(nums[i]);
            }
        }
        return res;
    }
}
