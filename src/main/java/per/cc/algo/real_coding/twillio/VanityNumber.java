package per.cc.algo.real_coding.twillio;


import java.nio.channels.NonReadableChannelException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class VanityNumber {
    static HashMap<Character, Character> map = new HashMap<>();
    static {
        map.put('A', '2');
        map.put('B', '2');
        map.put('C', '2');
        map.put('D', '3');
        map.put('E', '3');
        map.put('F', '3');
        map.put('G', '4');
        map.put('H', '4');
        map.put('I', '4');
        map.put('J', '5');
        map.put('K', '5');
        map.put('L', '5');
        map.put('M', '6');
        map.put('N', '6');
        map.put('O', '6');
        map.put('P', '7');
        map.put('Q', '7');
        map.put('R', '7');
        map.put('S', '7');
        map.put('T', '8');
        map.put('U', '8');
        map.put('V', '8');
        map.put('W', '9');
        map.put('X', '9');
        map.put('Y', '9');
        map.put('Z', '9');
    }
    public static void main(String[] args) {
        List<String> res = cal(Arrays.asList(
                "JAZZ",
                "ZIZZ",
                "BUZZ",
                "FUZZ",
                "BAZZ",
                "FIZZ",
                "HAJJ",
                "JUJU",
                "QUIZ",
                "JEEZ",
                "RAZZ",
                "TIZZ",
                "JEUX",
                "JINX",
                "JACK",
                "JOCK",
                "JUMP"), Arrays.asList("+14145894255"));
        res.forEach(System.out::println);
    }

    private static List<String> cal(List<String> vn, List<String> nums){
        List<String> conVn = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (String s : vn) {
            for(int i = 0; i < s.length(); i++){
                sb.append(map.get(s.charAt(i)));
            }
            conVn.add(sb.toString());
            sb.setLength(0);
        }
        List<String> res = new ArrayList<>();
        for (String n : nums) {
            for (String s : conVn) {
                if(n.contains(s)){
                    res.add(n);
                    res.add(s);
                    break;
                }
            }
        }
        res.sort(String::compareTo);
        return res;
    }

}
