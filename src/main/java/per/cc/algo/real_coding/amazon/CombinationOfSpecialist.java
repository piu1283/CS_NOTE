package per.cc.algo.real_coding.amazon;

import java.util.ArrayList;
import java.util.List;

/**
 * 第一题是给一个专家技能值list, minLeve, maxLevel和人数下限, 求出多少种组合. 挺容易的就是筛选出candidate后,
 * 根据人数下限以及candidate数量算出数量就好了
 */
public class CombinationOfSpecialist {
    public static void main(String[] args) {
        System.out.println(calc(new int[]{10,11,12,13,14,15}, 11, 15, 3));
    }

    private static int calc(int [] list, int min, int max, int lowNum){
        List<Integer> tmp = new ArrayList<>();
        for (int i : list) {
            if (i >= min && i <= max) {
                tmp.add(i);
            }
        }
        // 计算 Cm(lowNum) to Cm(m)
        if (lowNum > tmp.size()) {
            return 0;
        }
        int sum = 0;
        int n = tmp.size();
        for (int i = lowNum; i <= n; i++) {
            sum += getC(i, n);
        }
        return sum;
    }

    private static long getC(int m, int n){
        long ans = 1;
        if(m < n-m) m = n-m;
        for(int i = m+1; i <= n; i++) ans *= i;
        for(int j = 1; j <= n - m; j++) ans /= j;
        return ans;
    }
}
