package per.cc.tmplate.binary_search;


import java.util.*;

/**
 * this is a solution using binary search
 * or, we can using a TreeSet to sort the distinct element in A, and conduct a binary search above that.
 *
 * this code worth dig into,
 */
class MinimumNumberOfDaysToMakeMBouquets_binarySearch01 {
    public int minDays(int[] A, int m, int k) {
        int n = A.length, left = 1, right = (int) 20;
        if (m * k > n) return -1;
        while (left < right) {
            int mid = (left + right) / 2, flow = 0, bouq = 0;
            for (int j = 0; j < n; ++j) {
                if (A[j] > mid) {
                    flow = 0;
                } else if (++flow >= k) {
                    bouq++;
                    flow = 0;
                }
            }
            if (bouq < m) {
                left = mid + 1;
            } else {
                // this line is important
                right = mid;
            }
        }
        return left;
    }
}

/**
 * the UF method is TEL
 */
class MinimumNumberOfDaysToMakeMBouquets_UF {
    public int minDays_UF(int[] bloomDay, int m, int k) {
        if (m * k > bloomDay.length) {
            return -1;
        }
        TreeMap<Integer, List<Integer>> bmap = new TreeMap<>();
        int n = bloomDay.length;
        int hold = m * k;
        int bcount = 0;
        UF uf = new UF(n);
        for (int i = 0; i < n; i++) {
            if (bmap.containsKey(bloomDay[i])) {
                bmap.get(bloomDay[i]).add(i);
            } else {
                List<Integer> t = new ArrayList<>();
                t.add(i);
                bmap.put(bloomDay[i], t);
            }
        }
        for (Map.Entry<Integer, List<Integer>> entry : bmap.entrySet()) {
            List<Integer> t = entry.getValue();
            for (int i : t) {
                bloomDay[i] = -1;
                bcount++;
                boolean join = false;
                if (i > 0 && bloomDay[i - 1] == -1) {
                    uf.union(i, i - 1);
                    join = true;
                }
                if (i < n - 1 && bloomDay[i + 1] == -1) {
                    uf.union(i, i + 1);
                    join = true;
                }
                if (!join) {
                    uf.map.put(i, 1);
                }
            }
            if (bcount >= hold && check(uf, m, k)) {
                return entry.getKey();
            }
        }
        return -1;
    }

    private boolean check(UF uf, int m, int k) {
        int count = 0;
        for (Map.Entry<Integer, Integer> entry : uf.map.entrySet()) {
            count = entry.getValue() / k;
            m -= count;
            if (m <= 0) {
                return true;
            }
        }
        return false;
    }
}

class UF {

    public int[] parent;  // parent[i] = parent of i
    public int[] rank;   // rank[i] = rank of subtree rooted at i (never more than 31)
    public Map<Integer, Integer> map;

    public UF(int n) {
        if (n < 0) throw new IllegalArgumentException();
        parent = new int[n];
        map = new HashMap<>();
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    public int find(int p) {
        while (p != parent[p]) {
            p = parent[p];
        }
        return p;
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;
        if (rank[rootP] < rank[rootQ]) {
            parent[rootP] = rootQ;
            rank[rootQ]++;
            if (map.containsKey(rootP)) {
                map.put(rootQ, map.getOrDefault(rootQ, 1) + map.get(rootP));
                map.remove(rootP);
            } else {
                map.put(rootQ, map.getOrDefault(rootQ, 1) + 1);
            }

        } else {
            parent[rootQ] = rootP;
            rank[rootP]++;
            if (map.containsKey(rootQ)) {
                map.put(rootP, map.getOrDefault(rootP, 1) + map.get(rootQ));
                map.remove(rootQ);
            } else {
                map.put(rootP, map.getOrDefault(rootP, 1) + 1);
            }
        }
    }

}
