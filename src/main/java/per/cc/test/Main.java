package per.cc.test;

import java.util.*;
public class Main {
    public static void main(String[] args) {
//        ArrayList<ArrayList<Integer>> subsets = subsets(new int[]{1, 2, 3});
//        String s = solve("1", "99");
        GetNumberOfK(new int[]{1,2,3,3,3,3,4,5}, 3);
        System.out.println();
    }
    public static ArrayList<ArrayList<Integer>> subsets(int[] S) {
        Arrays.sort(S);
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        dfs(res, new ArrayList<>(), 0, S);
        return res;
    }

    private static void dfs(ArrayList<ArrayList<Integer>> res, ArrayList<Integer> cur, int idx, int [] s){
        res.add(new ArrayList<>(cur));
        if(idx == s.length){
            return;
        }
        for(int i = idx; i < s.length; i++){
            cur.add(s[i]);
            dfs(res,cur, i + 1, s);
            cur.remove(cur.size() - 1);
        }
    }

    public static String solve (String s, String t) {
        // write code here
        char [] sChar = new StringBuilder(s).reverse().toString().toCharArray();
        char [] tChar = new StringBuilder(t).reverse().toString().toCharArray();
        int lenS = s.length();
        int lenT = t.length();
        int maxLen = lenS > lenT?lenS: lenT;
        int[] res = new int[maxLen + 1];
        for(int i = 0; i < maxLen; i++){
            int a = i < sChar.length ? sChar[i] - '0' : 0;
            int b = i < tChar.length ? tChar[i] - '0' : 0;
            res[i] = a + b;
        }
        for(int i = 0; i < maxLen; i++){
            if(res[i] >= 10){
                res[i + 1]++;
                res[i] = res[i] % 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < maxLen; i++){
            sb.append(res[i]);
        }
        return sb.reverse().toString();
    }

    public static int GetNumberOfK(int [] array , int k) {
        int left = 0;
        int right = array.length;
        while(left < right){
            int mid = left + (right - left) / 2;
            if(array[mid] >= k){
                right = mid;
            }else {
                left = mid + 1;
            }
        }
        if(left >= array.length || array[left] != k){
            return 0;
        }else{
            int cnt = 0;
            for(int i = left; i>=0; i--){
                if(array[left] == k){
                    cnt++;
                }else{
                    break;
                }
            }
            return cnt;
        }
    }
}