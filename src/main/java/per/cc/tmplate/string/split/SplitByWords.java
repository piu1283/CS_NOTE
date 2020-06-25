package per.cc.tmplate.string.split;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Chen on 6/23/20.
 */
public class SplitByWords {
    public static void main(String[] args) {
        String s = "    I want    to walk my dog,    and why not?";
        String[] words = s.split("\\s+");
        for (String word : words) {
            System.out.print(word + ",");
        }
    }
}
