package per.cc.algo.find_majority;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

// the implementaion in pdf is wrong, java version see this
public class MajorityElements {
    public static List<Integer> majorityElement(final int[] nums) {
        // using boyer moore
        // generized version
        int k = 3;
        HashMap<Integer, Integer> candidate = new HashMap<>();

        for (final int i : nums) {
            if (candidate.containsKey(i)) {
                candidate.put(i, candidate.get(i) + 1);
                continue;
            }
            if (candidate.size() == k) {
                Iterator<Map.Entry<Integer, Integer>> it = candidate.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<Integer, Integer> t = it.next();
                    if (t.getValue() <= 1) {
                        it.remove();
                    } else {
                        t.setValue(t.getValue() - 1);
                    }
                }
            }
            if (candidate.size() < k) {
                candidate.put(i, 1);
            }
        }
        final List<Integer> res = new ArrayList<>();
        final int num = nums.length / k;
        for (final int i : candidate.keySet()) {
            int count = 0;
            for (final int j : nums) {
                if (i == j)
                    count++;
            }
            if (count > num)
                res.add(i);
        }
        return res;
    }

    public static void main(String[] args) {
        List<Integer> res = majorityElement(new int[] { 1, 2, 2, 3, 3, 4, 4, 5, 1, 1, 1, 1 });
        System.out.println(res);
    }

}