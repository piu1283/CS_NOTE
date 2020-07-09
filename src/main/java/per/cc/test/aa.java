package per.cc.test;

import java.util.Arrays;

/**
 * Created by Chen on 7/8/20.
 */
public class aa {
    public static void main(String[] args) {
        char[] bc = new char[4];
        Arrays.fill(bc, ' ');
        StringBuilder sb = new StringBuilder();
        sb.append("is").append(String.valueOf(bc, 0, 2));
        System.out.println(sb.toString());
    }
}
