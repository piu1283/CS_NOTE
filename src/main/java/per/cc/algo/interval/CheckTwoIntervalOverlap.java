package per.cc.algo.interval;

/**
 * Created by Chen on 7/4/20.
 */
public class CheckTwoIntervalOverlap {
    public static void main(String[] args) {
        int [] a = new int[]{1,7};
        int [] b = new int[]{4,6};
        System.out.println(overlap(a[0],a[1], b[0],b[1]));
    }

    private static boolean overlap(int l1, int r1, int l2, int r2){
        return Math.max(l1, l2) < Math.min(r1, r2);
    }
}
