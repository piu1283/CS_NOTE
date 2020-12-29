package per.cc.algo.math;

/**
 * https://blog.csdn.net/qq_19782019/article/details/85621386
 */
public class QuickPow {
    static int mod = 1000_000_007;
    public static void main(String[] args) {
        System.out.println(cal(2,100000000));
    }
    private static long cal(long base, long pow){
        long res = 1L;
        while(pow > 0){
            if(pow % 2 == 0){
                pow = pow / 2;
                base = (base * base) % mod;
            }else{
                pow -= 1;
                res = ((res % mod) * base) % mod;
                pow = pow / 2;
                base = (base * base) % mod;
            }
        }
        return res;
    }
}
