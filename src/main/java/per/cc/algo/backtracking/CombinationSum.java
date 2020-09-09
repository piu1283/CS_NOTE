package per.cc.algo.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique
 * combinations in candidates where the candidate numbers sums to target.
 *
 * The same repeated number may be chosen from candidates unlimited number of times.
 *
 * Note:
 *
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * Example 1:
 *
 * Input: candidates = [2,3,6,7], target = 7,
 * A solution set is:
 * [
 *   [7],
 *   [2,2,3]
 * ]
 * Example 2:
 *
 * Input: candidates = [2,3,5], target = 8,
 * A solution set is:
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 *
 *
 * Constraints:
 *
 * 1 <= candidates.length <= 30
 * 1 <= candidates[i] <= 200
 * Each element of candidate is unique.
 * 1 <= target <= 500
 */
class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, new ArrayList<>(), 0, 0, candidates, target);
        return new ArrayList<>(res);
    }

    private void dfs(List<List<Integer>> res, List<Integer> cur, int idx, int sum, int[] candi, int target){
        if(sum > target){
            return;
        }
        if(sum == target){
            res.add(new ArrayList<>(cur));
            return;
        }
        for(int i = idx; i < candi.length; i++){
            cur.add(candi[i]);
            dfs(res, cur, i, sum + candi[i], candi, target);
            cur.remove(cur.size() - 1);
        }
    }
}
