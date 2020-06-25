package per.cc.tmplate.node_distance_in_cycle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * https://leetcode.com/problems/group-shifted-strings/
 * Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can
 * keep "shifting" which forms the sequence:
 *
 * "abc" -> "bcd" -> ... -> "xyz"
 * Given a list of non-empty strings which contains only lowercase alphabets, group all strings that belong to the
 * same shifting sequence.
 *
 * Example:
 *
 * Input: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"],
 * Output:
 * [
 *   ["abc","bcd","xyz"],
 *   ["az","ba"],
 *   ["acef"],
 *   ["a","z"]
 * ]
 */
class GroupShiftedStrings {
    // "az" -> "ba" => ((a - b) + 26) % 26 = ((z - a) + 26) % 26
    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> res = new ArrayList<>();
        if (strings.length < 1){
            return res;
        }
        HashMap<Integer, List<List<String>>> map = new HashMap<>();
        for(String s : strings){
            if (!map.containsKey(s.length())){
                map.put(s.length(), new ArrayList<List<String>>());
                List<String> t = new ArrayList<>();
                t.add(s);
                map.get(s.length()).add(t);
            }else{
                boolean hasGroup = false;
                List<List<String>> list = map.get(s.length());
                for(int i = 0; i < list.size(); i++){
                    if (belongTo(list.get(i), s)){
                        list.get(i).add(s);
                        hasGroup = true;
                    }
                }
                if(!hasGroup){
                    List<String> tmp = new ArrayList<>();
                    tmp.add(s);
                    list.add(tmp);
                }
            }
        }
        for(List<List<String>> l : map.values()){
            res.addAll(l);
        }
        return res;
    }

    private boolean belongTo(List<String> l, String s){
        String t = l.get(0);
        int sv = s.charAt(0) - 'a';
        int tv = t.charAt(0) - 'a';
        int diff = ((sv - tv) + 26) % 26;
        for(int i = 1; i < t.length(); i++){
            sv = s.charAt(i) - 'a';
            tv = t.charAt(i) - 'a';
            if(((sv - tv) + 26) % 26 != diff){
                return false;
            }
        }
        return true;
    }
}
