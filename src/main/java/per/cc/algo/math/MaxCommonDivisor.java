package per.cc.algo.math;

public class MaxCommonDivisor {
    public static void main(String[] args) {
        System.out.println(mcd(32,18));
    }

    private static int mcd(int a, int b){
        if(a < b){
            return mcd(b,a);
        }
        if (a % b == 0) {
            return b;
        }
        return mcd(b, a % b);
    }
}
