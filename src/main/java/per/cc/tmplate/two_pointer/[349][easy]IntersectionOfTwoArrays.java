package per.cc.tmplate.two_pointer;

import java.util.*;

/**
 * Given two arrays, write a function to compute their intersection.
 *
 * Example 1:
 *
 * Input: nums1 = [1,2,2,1], nums2 = [2,2]
 * Output: [2]
 * Example 2:
 *
 * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * Output: [9,4]
 */
class IntersectionOfTwoArrays_set {
    public int[] intersection_(int[] nums1, int[] nums2) {
        List<Integer> res = new ArrayList<>();
        if (nums1.length < 0 || nums2.length < 0) {
            return new int[0];
        }
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        for (int i : nums1) {
            set1.add(i);
        }
        for (int i : nums2) {
            set2.add(i);
        }
        Set<Integer> less = set1.size() > set2.size() ? set2 : set1;
        Set<Integer> more = set1.size() > set2.size() ? set1 : set2;
        for (int i1 : less) {
            if (more.contains(i1)) {
                res.add(i1);
            }
        }
        return res.stream().mapToInt(Integer::intValue).toArray();
    }
}

class IntersectionOfTwoArrays_binarysearch {
    public int[] intersection__(int[] nums1, int[] nums2) {
        ArrayList<Integer> integers = new ArrayList<>();
        Arrays.sort(nums1);

        for (int i : nums2) {
            int pos = Arrays.binarySearch(nums1, i);
            if ((pos >= 0) && !integers.contains(nums1[pos])) {
                integers.add(nums1[pos]);
            }
        }

        return integers.stream().mapToInt(Integer::intValue).toArray();
    }
}

class IntersectionOfTwoArrays_twoPointer {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0;
        int j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                set.add(nums1[i]);
                i++;
                j++;
            }
        }
        return set.stream().mapToInt(Integer::intValue).toArray();
    }
}
