package per.cc.algo.string.split;

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
