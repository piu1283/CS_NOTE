package per.cc.algo.two_pointer;

/**
 * count the number of sub array sum which less than k
 */
public class CountSubarraySumLessThanK {
    public static void main(String[] args) {
        System.out.println(countSubArraySumRank(new int[]{4,3,1,2}, 5));
    }

    private static int countSubArraySumRank(int [] nums, int k){
        int j = 0;
        int sum = 0;
        int count = 0;
        for(int i = 0; i < nums.length; i++){
            while (j < nums.length && sum + nums[j] < k){
                sum += nums[j];
                j++;
            }
            count += j - i;
            sum -= nums[i];
        }
        return count;
    }
}
