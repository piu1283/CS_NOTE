package per.cc.tmplate.find_max_left_and_max_right_for_each_element;

/**
 * Created by Chen on 6/24/20.
 */
public class UsingDP {
    public static void dpSolution(int[] arr) {
        // the left and rigth arr stores the left most element and right most element
        // for each element
        int[] left = new int[arr.length];
        int[] right = new int[arr.length];
        left[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            left[i] = Math.max(left[i - 1], arr[i]);
        }
        right[arr.length - 1] = arr[arr.length - 1];
        for (int i = arr.length - 2; i >= 0; i--) {
            right[i] = Math.max(arr[i], right[i + 1]);
        }
    }
}
