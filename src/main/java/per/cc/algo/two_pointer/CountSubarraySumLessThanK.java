package per.cc.algo.two_pointer;

import com.sun.tools.classfile.SourceDebugExtension_attribute;

/**
 * count the number of sub array sum which less than k
 */
public class CountSubarraySumLessThanK {
    public static void main(String[] args) {
        System.out.println(countSubArraySumRank(new int[]{4,3,1,2}, 5));
        System.out.println(search("aaabb", 3,1));
    }

    private static boolean search(String s, int len, int k){
        char [] srr = s.toCharArray();
        int i = 0;
        int j = 0;
        int [] arr = new int[26];
        while(j < srr.length){
            if(j < i + len){
                arr[srr[j] - 'a']++;
                j++;
            }
            if (j - i == len){
                System.out.println(s.substring(i,j));
                arr[srr[i] - 'a']--;
                i++;
            }
        }
        return false;
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
