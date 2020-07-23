package per.cc.algo.brain_teaser.permutation;

import java.util.LinkedList;

/**
 * The set [1,2,3,...,n] contains a total of n! unique permutations.
 *
 * By listing and labeling all of the permutations in order, we get the following sequence for n = 3:
 *
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * Given n and k, return the kth permutation sequence.
 *
 * Note:
 *
 * Given n will be between 1 and 9 inclusive.
 * Given k will be between 1 and n! inclusive.
 * Example 1:
 *
 * Input: n = 3, k = 3
 * Output: "213"
 * Example 2:
 *
 * Input: n = 4, k = 9
 * Output: "2314"
 */
class PermutationSequence {
    // this is the best way
    // https://www.youtube.com/watch?v=xdvPD1IiyUM
    // a little bit tricky
    // O(n^2)
    public String getPermutation(int n, int k) {
        int [] f = new int[n + 1];
        char[] res = new char[n];
        f[0] = 1;
        LinkedList<Integer> list = new LinkedList<>();
        for(int i = 1; i <= n; i++){
            f[i] = f[i - 1] * i;
            list.add(i);
        }
        k--;
        for(int i = 0; i < n; i++){
            int idx = k / f[n - 1 - i];
            res[i] = (char)(list.remove(idx) - 1 + '1');
            k %= f[n - 1 - i];
        }
        return String.valueOf(res);
    }

    // the normal way
    // O(n*k)
    public String getPermutation_(int n, int k) {
        int times = 1;
        char[] crr = new char[n];
        for(int i = 1; i <= n; i++){
            times *= i;
            crr[i - 1] = (char)('1' + i - 1);
        }
        if(k == 1){
            return String.valueOf(crr);
        }
        if(times == k){
            reverse(crr,0, crr.length - 1);
            return String.valueOf(crr);
        }
        times = 1;
        while(times < k){
            nxt(crr);
            times++;
        }
        return String.valueOf(crr);
    }

    private void nxt(char[] crr){
        for(int i = crr.length - 1; i > 0; i--){
            if(crr[i] > crr[i - 1]){
                for(int j = crr.length - 1; j >= i ; j--){
                    if(crr[j] > crr[i - 1]){
                        swap(crr, i - 1 ,j);
                        reverse(crr, i, crr.length - 1);
                        return;
                    }
                }
            }
        }
    }

    private void reverse(char[] c, int start, int end){
        while(start < end){
            swap(c, start, end);
            start++;
            end--;
        }
    }

    private void swap(char[] c, int i, int j){
        char tmp = c[i];
        c[i] = c[j];
        c[j] = tmp;
    }
}
