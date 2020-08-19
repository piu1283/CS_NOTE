package per.cc.algo.real_coding.amazon;

import java.util.Arrays;

/**
 * 第二题是给一个String数字, 然后求能分割成多少种纯prime number组合. 用recursion
 * https://leetcode.com/discuss/interview-question/593211/count-the-number-of-ways-the-string-can-split-to-get-pime-number
 */
public class CutPrime {
    public static void main(String[] args) {
        buildSieve();

        String s1 = "31753175317531753175";

        System.out.print(countPrimeStrings(s1) + "\n");
    }

    static final int MOD = 1000000007;
    static boolean[] sieve = new boolean[1000000];

    static int countPrimeStrings(String number) {
        int n = number.length();
        int[] dp = new int[n + 1];

        Arrays.fill(dp, -1);
        dp[0] = 1;

        return rec(number, n, dp);
    }

    static void buildSieve() {
        Arrays.fill(sieve, true);

        sieve[0] = false;
        sieve[1] = false;

        for (int p = 2; p * p <= 1000000; p++) {

            // If p is a prime
            if (sieve[p] == true) {

                // Update all multiples
                // of p as non prime
                for (int i = p * p; i < 1000000;
                     i += p)
                    sieve[i] = false;
            }
        }
    }

    private static int rec(String number, int i, int[] dp) {
        if (dp[i] != -1)
            return dp[i];
        int cnt = 0;

        for (int j = 1; j <= 6; j++) {

            // Number should not have a
            // leading zero and it
            // should be a prime number
            if(j == 6 && i - j >= 0)
                System.out.println();
            if (i - j >= 0 && number.charAt(i - j) != '0' && isPrime(number.substring(i - j, i))) {
                cnt += rec(number, i - j, dp);
                cnt %= MOD;
            }
        }
        return dp[i] = cnt;
    }

    private static boolean isPrime(String s) {
        int num = Integer.parseInt(s);
        return sieve[num];
    }

}
