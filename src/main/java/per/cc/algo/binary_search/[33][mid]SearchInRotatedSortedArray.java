package per.cc.algo.binary_search;

/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * <p>
 * (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
 * <p>
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 * <p>
 * You may assume no duplicate exists in the array.
 * <p>
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 * Example 2:
 * <p>
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 */
class SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        if (nums.length < 1) {
            return -1;
        }
        if (nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int pivot = findOrigin(nums, 0, nums.length - 1);
        // return pivot;
        if (target >= nums[pivot] && target <= nums[nums.length - 1]) {
            //search left
            return binarySearch(nums, pivot, nums.length - 1, target);
        } else if (pivot == 0 || (target <= nums[pivot - 1] && target >= nums[0])) {
            return binarySearch(nums, 0, pivot - 1, target);
        } else {
            return -1;
        }
    }

    // this function is vital, for non-duplicated array we can use this
    public int findOrigin(int[] nums, int l, int r) {
        while (l < r) {
            int m = l + (r - l) / 2;
            if (nums[m] > nums[r]) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        return l;
    }

    // for array contains duplicated element we can just go through it
    private int findPivot(int[] nums, int left, int right) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                return i + 1;
            }
        }
        return 0;
    }

    public int binarySearch(int[] nums, int start, int end, int target) {
        while (start <= end) {
            int mid = (start + end) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }
}
