package per.cc.algo.binary_search;

import java.util.Arrays;

/**
 * Created by Chen on 7/9/20.
 */

class KokoEatingBananas {
    public int minEatingSpeed(int[] piles, int H) {
        // first we found the upper and lower bound
        // low[min(piles)]-up[max(piles)]
        int left = 1;
        int right = 1_000_000_000;
        // can search from fix left and right, it will be constant, and can save a for-loop
        // for(int i : piles){
        //     right = Math.max(right,i);
        // }
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(canEat(piles, H, mid)){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean canEat(int[] piles, int H, int n){
        int count = 0;
        for(int i:piles)
            count+=(int)Math.ceil(i*1.0/n);
        if(count>H)
            return false;
        else return true;
    }
}
