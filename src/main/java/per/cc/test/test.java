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
//        LinkedList<Integer> list = new LinkedList<>();
//        String aa = "aa";
//
//        Calendar cal = Calendar.getInstance();
//        cal.set(Calendar.YEAR, 2020);
//        cal.set(Calendar.MONTH, 9);
//        cal.set(Calendar.DAY_OF_WEEK, 3);
//        cal.set(Calendar.DAY_OF_WEEK_IN_MONTH, 2);
//        Date d = cal.getTime();
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        PriorityQueue<Map.Entry<Integer, Integer>> que = new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));
        int [] arr = new int[]{1,3,4,7,9,12};
        int i = Arrays.binarySearch(arr, 6);
        int [][] a = new int[][]{{1,2},{0,3},{1,1}};
        Arrays.sort(a,(a1,a2)->{
            if(a1[0] == a2[0]){
                return a1[1] - a2[1];
            }
            return a1[0] - a2[0];
        });
        System.out.println(i);
    }
}
