package per.cc.test;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int [] arr = new int[m];
        int [] tmp = new int[m];
        for(int i = 0; i < m; i++){
            arr[i] = sc.nextInt();
            tmp[i] = arr[i];
        }
        int [] res = calc(tmp, arr, n);
        for(int i = 0; i < res.length; i++){
            if(i == res.length - 1){
                System.out.print(res[i]);
            }else{
                System.out.print(res[i] + " ");
            }
        }
    }

    private static int[] calc(int[] tmp, int[] arr, int n){
        Arrays.sort(tmp);
        int m = arr.length;
        int[] pre = new int[n - arr.length];
        int[] res = new int[n];
        int pos = 0, i = 1, j = 0;
        while(pos < pre.length || j < m){
            if(pos >= pre.length) break;
            if(j >= m || i!=tmp[j]){
                pre[pos] = i;
                pos++;
            }
            if(j < m && i == tmp[j]) j++;
            i++;
        }
        int mIdx = 0, nIdx = 0, preIdx = 0;;
        while(nIdx < n){
            if(mIdx >= arr.length){
                res[nIdx] = pre[preIdx];
                preIdx++;
                nIdx++;
                continue;
            }
            if(preIdx >= pre.length){
                res[nIdx] = arr[mIdx];
                mIdx++;
                nIdx++;
                continue;
            }
            if(arr[mIdx] < pre[preIdx]){
                res[nIdx] = arr[mIdx];
                mIdx++;
            }else{
                res[nIdx] = pre[preIdx];
                preIdx++;
            }
            nIdx++;
        }
        return res;
    }
}