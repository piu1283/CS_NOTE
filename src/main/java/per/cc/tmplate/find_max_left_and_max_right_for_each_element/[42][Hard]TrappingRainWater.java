package per.cc.tmplate.find_max_left_and_max_right_for_each_element;

/**
 * Created by Chen on 6/24/20.
 */
class TrappingRainWater {
    // using two pointers
    // time O(n), space O(1)
    public int trap_twoPointer(int[] height) {
        // corner cases
        if(height.length < 3){
            return 0;
        }
        int res = 0;
        int lMax = 0;
        int rMax = 0;
        int i = 0;
        int j = height.length - 1;
        while(i <= j){
            lMax = Math.max(lMax, height[i]);
            rMax = Math.max(rMax, height[j]);
            if(lMax <= rMax){
                res += lMax - height[i];
                i++;
            }else{
                res += rMax - height[j];
                j--;
            }
        }
        return res;
    }
}
