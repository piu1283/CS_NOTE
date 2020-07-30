package per.cc.algo.greedy;

/**
 * Given an array of positive integers target and an array initial of same size with all zeros.
 * <p>
 * Return the minimum number of operations to form a target array from initial if you are allowed to do the following
 * operation:
 * <p>
 * Choose any subarray from initial and increment each value by one.
 * The answer is guaranteed to fit within the range of a 32-bit signed integer.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: target = [1,2,3,2,1]
 * Output: 3
 * Explanation: We need at least 3 operations to form the target array from the initial array.
 * [0,0,0,0,0] increment 1 from index 0 to 4 (inclusive).
 * [1,1,1,1,1] increment 1 from index 1 to 3 (inclusive).
 * [1,2,2,2,1] increment 1 at index 2.
 * [1,2,3,2,1] target array is formed.
 * Example 2:
 * <p>
 * Input: target = [3,1,1,2]
 * Output: 4
 * Explanation: (initial)[0,0,0,0] -> [1,1,1,1] -> [1,1,1,2] -> [2,1,1,2] -> [3,1,1,2] (target).
 * Example 3:
 * <p>
 * Input: target = [3,1,5,4,2]
 * Output: 7
 * Explanation: (initial)[0,0,0,0,0] -> [1,1,1,1,1] -> [2,1,1,1,1] -> [3,1,1,1,1]
 * -> [3,1,2,2,2] -> [3,1,3,3,2] -> [3,1,4,4,2] -> [3,1,5,4,2] (target).
 * Example 4:
 * <p>
 * Input: target = [1,1,1,1]
 * Output: 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= target.length <= 10^5
 * 1 <= target[i] <= 10^5
 */
class MinimumNumberOfIncrementsOnSubarraysToFormATargetArray {
    public int minNumberOperations_greedy(int[] target) {
        // this problem can be solved using greedy
        // https://www.youtube.com/watch?v=RWWE1ccmKrY
        int res = 0;
        int cur = 0;
        for(int i = 0; i < target.length; i++){
            if(target[i] > cur){
                res += target[i] - cur;
            }
            cur = target[i];
        }
        return res;
    }


    // or we can use divede conqur
    // https://www.youtube.com/watch?v=RWWE1ccmKrY
    int res = 0;
    public int minNumberOperations_dc(int[] target) {
        DFS(target, 0, target.length - 1, 0);
        return res;
    }

    void DFS(int[] target, int start, int end, int base){
        if(start > end){
            return;
        }
        int[] tmp = findMin(start, end, target);
        int minVal = tmp[0];
        int minPos = tmp[1];
        res += minVal - base;
        DFS(target,start, minPos - 1, minVal);
        DFS(target, minPos + 1, end, minVal);
    }

    private int[] findMin(int start, int end, int[] target){
        int [] res = new int[2];
        res[0] = Integer.MAX_VALUE;
        for(int i = start; i <= end; i++){
            if(res[0] > target[i]){
                res[0] = target[i];
                res[1] = i;
            }
        }
        return res;
    }
}
