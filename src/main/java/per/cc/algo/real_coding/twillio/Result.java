package per.cc.algo.real_coding.twillio;

import java.util.*;

class Result {

    public static void main(String[] args) {
        List<String> res = vanity(Arrays.asList(
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

    /*
     * Complete the 'vanity' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts following parameters:
     *  1. STRING_ARRAY codes
     *  2. STRING_ARRAY numbers
     */

    static HashMap<Character,Character> map = new HashMap<>();
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



    public static List<String> vanity(List<String> codes, List<String> numbers) {
        // form the tireNode tree
        TireNode root = new TireNode();
        for(String c : codes){
            TireNode cur = root;
            for(int i = 0; i < c.length(); i++){
                char tmp = map.get(c.charAt(i));
                if(cur.nodes[tmp - '0'] != null){
                    cur = cur.nodes[tmp - '0'];
                }else{
                    TireNode t = new TireNode();
                    cur.nodes[tmp - '0'] = t;
                    cur = t;
                }
            }
            cur.isENd = true;
        }

        // search each numbers in tireNode tree
        List<String> res = new ArrayList<>();
        for(String s : numbers){
            TireNode cur = root;
            for(int i = 0; i < s.length(); i++){
                if(Character.isDigit(s.charAt(i))){
                    if(cur.nodes[s.charAt(i) - '0'] != null){
                        cur = cur.nodes[s.charAt(i) - '0'];
                        if(cur.isENd){
                            res.add(s);
                            break;
                        }
                    }else{
                        cur = root;
                        if(cur.nodes[s.charAt(i) - '0'] != null){
                            cur = cur.nodes[s.charAt(i) - '0'];
                        }
                    }
                }else{
                    cur = root;
                }
            }
        }
        Collections.sort(res);
        return res;
    }

}

class TireNode {
    TireNode [] nodes = new TireNode[10];
    boolean isENd = false;
}
