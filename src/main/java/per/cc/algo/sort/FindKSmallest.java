package per.cc.algo.sort;

import java.util.ArrayList;
import java.util.Arrays;

public class FindKSmallest {
    private static int[] getTopKGreatest(final int[] nums, final int k) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int s = left - 1;
            final int pivot = nums[right];
            for (int e = left; e < right; e++) {
                // if want to get top k smallest
                // change to <=
                if (nums[e] >= pivot) {
                    s++;
                    final int tmp = nums[e];
                    nums[e] = nums[s];
                    nums[s] = tmp;
                }
            }
            final int tmp = nums[right];
            nums[right] = nums[s + 1];
            nums[s + 1] = tmp;
            if ((s + 1) == k) {
                break;
            } else if ((s + 1) > k) {
                right = s;
            } else {
                left = s + 1;
            }
        }
        return Arrays.copyOf(nums, k);
    }
}
