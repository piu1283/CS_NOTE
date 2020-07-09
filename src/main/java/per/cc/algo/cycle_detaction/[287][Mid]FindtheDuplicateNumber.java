package per.cc.algo.cycle_detaction;

/**
 * 287. Find the Duplicate Number
 * (https://leetcode.com/problems/find-the-duplicate-number/)
 * 
 * Given an array nums containing n + 1 integers where each integer is between 1
 * and n (inclusive), prove that at least one duplicate number must exist.
 * Assume that there is only one duplicate number, find the duplicate one.
 * 
 * Example 1:
 * 
 * Input: [1,3,4,2,2] Output: 2 Example 2:
 * 
 * Input: [3,1,3,4,2] Output: 3
 * 
 * 
 * This problem is special, can use Floyd's Tortoise and Hare (Cycle Detection)
 * to solve achiving time O(n) space O(1)
 * 
 * Or , we can use set<Integer> which leads to time O(n), space O(n) or we can
 * using sorting , but it will modify the original arr, time O(nlogn), space
 * O(1)
 */
class FindTheDuplicateNumber {
    public int findDuplicate(int[] nums) {
        int slow = 0;
        int fast = 0;
        // phase 1
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        // phase 2
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}