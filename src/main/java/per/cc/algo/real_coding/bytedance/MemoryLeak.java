package per.cc.algo.real_coding.bytedance;

public class MemoryLeak {
    public static void main(String[] args) {
        cal(5,15);
        cal(2,2);
        cal(8, 11);
    }

    private static void cal(int M1, int M2) {
        int rst = 0;
        boolean f = false;
        if (M1 < M2) {
            int tmp = M1;
            M1 = M2;
            M2 = tmp;
            f = true;
        }
        int dif = M1 - M2;
        int n1 = vd(1, dif);
        int sum = getSum(1, n1, n1);
        if (sum > M1) {
            if (!f)
                System.out.println(n1 + " " + (M1 - getSum(1, n1 - 1, n1 - 1)) + " " + M2);
            else
                System.out.println(n1 + " " + M2 + " " + (M1 - getSum(1, n1 - 1, n1 - 1)));
            return;
        } else if (sum == dif) {
            f = true;
        }
        M1 = M1 - sum;
        int cnt1 = n1;
        int n2 = n1 + 1;
        n1 += 2;
        int rst1 = vd2(n1, M1);
        int rst2 = vd2(n2, M2);
        if (rst2 <= rst1) {
            int cnt2 = cnt1 + rst2 + rst2 - 1;
            M1 -= getSum(n1, n1 + (rst2 - 2) * 2, rst2 - 1);
            M2 -= getSum(n2, n2 + (rst2 - 2) * 2, rst2 - 1);
            if (!f)
                System.out.println(cnt2 + " " + M1 + " " + M2);
            else
                System.out.println(cnt2 + " " + M2 + " " + M1);
        } else {
            int cnt2 = cnt1 + rst1 + rst1;
            M1 -= getSum(n1, n1 + (rst1 - 2) * 2, rst1 - 1);
            M2 -= getSum(n2, n2 + (rst1 - 1) * 2, rst1);
            if (!f)
                System.out.println(cnt2 + " " + M1 + " " + M2);
            else
                System.out.println(cnt2 + " " + M2 + " " + M1);
        }
    }

    private static int getSum(int a, int an, int n) {
        return (a + an) * n / 2;
    }

    private static int vd(int a, int x) {
        double a1 = a, n = x;
        double rst = Math.sqrt(2 * n + (2 * a1 - 1) * (2 * a1 - 1) / 4) - (2 * a1 - 1) / 2;
        return (int) Math.ceil(rst);
    }

    private static int vd2(int a, int x) {
        double a1 = a, n = x;
        double rst = Math.sqrt(n + (a1 - 1) * (a1 - 1) / 4) - (a1 - 1) / 2;
        int rstInt = (int) Math.ceil(rst);
        if ((a + a + (rstInt - 1) * 2) * rstInt == 2 * n)
            return rstInt + 1;
        return rstInt;
    }
}
