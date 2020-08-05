package per.cc.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ttt{
    public static void main(String[] args) {
        String[] a = new String[]{"1","2"};
        int[] ints = Arrays.stream(a).mapToInt(Integer::parseInt).toArray();
        List<Integer> l = new ArrayList<>();
        l.add(null);
        l.add(null);
        l.set(1, 1);
        l.forEach(System.out::print);
    }
}
