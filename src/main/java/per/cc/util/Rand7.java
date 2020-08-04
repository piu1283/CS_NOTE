package per.cc.util;

import java.util.Random;

public class Rand7 {
    static Random r = new Random();
    public static int rand7(){
        return r.nextInt(7) + 1;
    }
}
