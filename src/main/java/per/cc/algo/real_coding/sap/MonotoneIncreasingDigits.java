package per.cc.algo.real_coding.sap;

/**
 * Given a non-negative integer N, find the largest number that is less than or equal to N with monotone increasing
 * digits.
 *
 * (Recall that an integer has monotone increasing digits if and only if each pair of adjacent digits x and y satisfy
 * x < y.)
 *
 * Example 1:
 * Input: N = 998
 * Output: 789
 *
 * Note: N is an integer in the range [0, 10^9].
 */
public class MonotoneIncreasingDigits {
    public static void main(String[] args) {
    }
    public int monotoneIncreasingDigits(int N) {
        String n = String.valueOf(N);
        char [] arr = n.toCharArray();
        int i = 1;
        for(; i < arr.length; i++){
            if(arr[i] <= arr[i - 1]){
                break;
            }
        }
        for(; i > 0; i--){
            if(arr[i - 1] >= arr[i])
                arr[i - 1]--;
        }
        for(int j= i + 1 ; j < arr.length; j++){
            arr[j] = '9';
        }
        char cur = '9';
        for(int j = arr.length - 1; j >= 0; j--){
            if(arr[j] >= cur){
                arr[j] = cur--;
            }
        }
        return Integer.parseInt(String.valueOf(arr));
    }
}
