package per.cc.algo.real_coding.luobinhood;

public class OccurrenceOfDigit {

    public static void main(String[] args) {
        System.out.println(calc(22));
    }

    private static int calc(int n){
        if(n < 0){
            return 0;
        }
        int res = 1;
        for(int i = 2;  i <= n;i++){
            res += getOne(i);
        }
        return res;
    }

    private static int getOne(int n){
        int sum = 0;
        while(n > 0){
            int tmp = n % 10;
            n /= 10;
            if(tmp == 2){
                sum++;
            }else if(tmp == 4){
                sum++;
            }else if(tmp == 0){
                sum++;
            }
        }
        return sum;
    }
}
