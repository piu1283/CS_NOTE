package per.cc.algo.number_system;

/**
 * Given a positive integer, return its corresponding column title as appear in an Excel sheet.
 *
 * For example:
 *
 *     1 -> A
 *     2 -> B
 *     3 -> C
 *     ...
 *     26 -> Z
 *     27 -> AA
 *     28 -> AB
 *     ...
 * Example 1:
 *
 * Input: 1
 * Output: "A"
 * Example 2:
 *
 * Input: 28
 * Output: "AB"
 * Example 3:
 *
 * Input: 701
 * Output: "ZY"
 */
class ExcelSheetColumnTitle {
    // the key point of this problem is about the "n--"
    // because the A is start from the 1,
    // so for 1, we need to make it get 0 after mod 26. The n-- is a good way to achieve this.
    // of course, we need to do n-- in every step.
    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        while(n >= 1){
            n--;
            int tmp = n % 26;
            n /= 26;
            sb.append((char) (tmp + 'A'));
        }
        sb = sb.reverse();
        return sb.toString();
    }
}
