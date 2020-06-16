package per.cc.test;


import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Chen on 1/20/20.
 */
public class test {
    static Map<String, String> map = new HashMap<>();
    static{
        map.put("&quot;","'");
    }
    public static void main(String[] args) {
        // find the date of the second Tuesday on Oct. in this year
        LinkedList<Integer> list = new LinkedList<>();
        String aa = "aa";

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2020);
        cal.set(Calendar.MONTH, 9);
        cal.set(Calendar.DAY_OF_WEEK, 3);
        cal.set(Calendar.DAY_OF_WEEK_IN_MONTH, 2);
        Date d = cal.getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(df.format(d));
        PriorityQueue<Map.Entry<Integer, Integer>> que = new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));
    }
}
