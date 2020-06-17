package per.cc.util;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Chen on 6/16/20.
 */
public class CodeSnip {
    public static void main(final String[] args) {
        // transfer int array to list
        final int[] arr = new int[] { 1, 2, 3, 4, 5 };
        final List<Integer> collect = Arrays.stream(arr).boxed().collect(Collectors.toList());

        // sort map by value
        final HashMap<Integer, Integer> map = new HashMap<>();
        final List<Integer> keyList = new ArrayList<>(map.keySet());
        Collections.sort(keyList, (a, b) -> map.get(a) - map.get(b));

        // put int[] in List
        final List<int[]> list = new ArrayList<>();
        // change list to int[]
        int[][] array = list.toArray(new int[list.size()][]);

        // convert ArrayList<Integer> to int[]
        final List<Integer> list2 = new ArrayList<>();
        int[] ints = list2.stream().mapToInt(Integer::intValue).toArray();
    }
}
