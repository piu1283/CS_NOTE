package per.cc.test;

class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int res = s.findLatestStep(new int[]{3,5,1,2,4}, 1);
        System.out.println(res);
    }
    public int findLatestStep(int[] arr, int m) {
        UF uf = new UF(arr.length);
        boolean[] visit = new boolean[arr.length];
        int ans = 0;
        int count = 0;
        for(int i = 0; i < arr.length; i++){
            int idx = arr[i] - 1;
            int left = idx - 1;
            if(left >= 0 && visit[left]){
                if(uf.getSize(left) == m) count--;
                uf.union(idx, left);
            }
            int right = idx + 1;
            if(right < arr.length && visit[right]){
                if(uf.getSize(right) == m) count--;
                uf.union(idx, right);
            }
            visit[idx] = true;
            if(uf.getSize(idx) == m){
                count++;
            }
            if(count > 0) ans = i + 1;
        }
        if(ans > 0) return ans;
        return -1;
    }
}

class UF {
    private int[] parent;   // parent[i] = parent of i
    private int[] size;     // size[i] = number of sites in subtree rooted at i
    private int count;      // number of components

    public UF(int n) {
        count = n;
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int getSize(int i){
        int root = find(i);
        return size[root];
    }

    public int count() {
        return count;
    }

    public int find(int p) {

        while (p != parent[p])
            p = parent[p];
        return p;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;

        // make smaller root point to larger one
        if (size[rootP] < size[rootQ]) {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }
        else {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
        count--;
    }

}
