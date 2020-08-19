package per.cc.algo.real_coding.luobinhood;

import java.util.*;

public class Other {
    public static void main(String[] args) {
        System.out.println(aaaa(10, new String[]{"L","L","C0","L","C3"}));
        System.out.println(aaaa(10, new String[]{"L","L","L","L","L","L","L","L","L","L","L","C0"}));
    }

    private static String aaaa(int n, String [] operations){
        int leftMost = 0;
        char [] arr = new char[n];
        for(int i = 0; i < n; i++){
            arr[i] = '0';
        }
        for (String s : operations) {
            if(s.equals("L")){
                if(arr[leftMost] != '0') continue;
                arr[leftMost] = '1';
                while (leftMost < arr.length && arr[leftMost] == '1') {
                    leftMost++;
                }
                if(leftMost == arr.length){
                    leftMost--;
                }
            }else{
                String idxStr = s.substring(1);
                int idx = Integer.parseInt(idxStr);
                if(idx < leftMost){
                    leftMost = idx;
                }
                arr[idx] = '0';
            }
        }
        return String.valueOf(arr);
    }

    /**
     * 给数组内的所有数字按频率排序然后从右下角交错输出，频率相等就按大小排。
     * 例子:
     * [[2,2,3],
     * [1,1,1],
     * [2,2,4]]
     *
     * 按频率排序结果:
     * [3, 4,1,1,1,2,2,2,2]
     *
     * 输出的时候从右下斜着填 (先填m[2][2], 然后m[2][1],然后m[1][2], 然后m[2][0], 然后[1][1] .... 最后m[0][0])
     * [[2,2,2],
     * [2,1,1],
     * [1,4,3]]
     */
    private static int[][] reorganizedByFreq(int[][] matrix){
        HashMap<Integer, Integer> map = new HashMap<>();
        int cnt = 0;
        for (int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++){
                map.put(matrix[i][j], map.getOrDefault(matrix[i][j], 0) + 1);
                cnt++;
            }
        }
        List<HashMap.Entry<Integer,Integer>> list = new ArrayList<>(map.entrySet());
        list.sort((a, b) -> {
            if (!a.getValue().equals(b.getValue())) return a.getValue() - b.getValue();
            else return a.getKey() - b.getKey();
        });
        int[] afterSort = new int [cnt];
        int pos = 0;
        for (Map.Entry<Integer, Integer> entry : list) {
            for (int i = 0; i < entry.getValue(); i++) {
                afterSort[pos++] = entry.getKey();
            }
        }
        pos = afterSort.length - 1;
        for(int col = 0; col < matrix[0].length; col++){
            int row = 0;
            int c = col;
            while (row < matrix.length && c >= 0) {
                matrix[row][c] = afterSort[pos++];
                row++;
                c--;
            }
        }
        for(int row = 1; row < matrix.length; row++){
            int col = matrix[0].length - 1;
            int r = row;
            while(r < matrix.length && col >= 0){
                matrix[r][col] = afterSort[pos++];
                r++;
                col--;
            }
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[0] - o1[0];
            }
        });
        return matrix;
    }

    /**
     * 给两个String array，判断第一个中的所有string是不是都是第二个中的string的顺序链接string。
     * 例子：
     * 输入 ["onetwo", "one"], ["one", "two", "three"]
     * 第二个String array 中的顺序链接为["one", "onetwo", "onetwothree"]，包括了所有第一个中的string，返回true。
     */
    private static boolean checkConnect(String[] a, String []b){
        for(String s : a){
            int pos = 0;
            int bPos = 0;
            while (pos < s.length() && bPos < b.length) {
                int nextPos = pos + b[bPos].length();
                if(nextPos > s.length()) return false;
                if (!s.substring(pos, nextPos).equals(b[bPos])) {
                    return false;
                }
                pos = nextPos;
                bPos++;
            }
            if (bPos == b.length && pos == s.length()) {
                continue;
            }
            if(pos < s.length() || bPos == b.length) {
                return false;
            }
        }
        return true;
    }

    /**
     * 一个没有重复数字的数组，将这个数组所有相邻的数，打乱顺序生成一个int[2]放进一个array里面, 再打乱顺序给你，让你返回原数组。
     * (倒序也是正确的)
     * 例子:
     * [3, 5, 1, 2, 4]
     * 有相邻pair [[3, 5], [5, 1], [1, 2], [2, 4]]
     * 打乱顺序后为[[4, 2], [1, 2], [5, 3], [5, 1]]
     *
     * 给你[[4, 2], [1, 2], [5, 3], [5, 1]], 你需要返回[3, 5, 1, 2, 4] 或[4, 2, 1, 5, 3]
     */
    private static List<Integer> restoreArray(int[][] input){
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        // generate graph
        for (int[] arr : input) {
            map.put(arr[0], map.getOrDefault(arr[0],new ArrayList<>()));
            map.get(arr[0]).add(arr[1]);
            map.put(arr[1], map.getOrDefault(arr[1],new ArrayList<>()));
            map.get(arr[1]).add(arr[0]);
        }
        int start = 0;
        for (Integer i : map.keySet()) {
            if (map.get(i).size() == 1) {
                start = i;
                break;
            }
        }
        Set<Integer> seen = new HashSet<>();
        int cur = start;
        List<Integer> res = new ArrayList<>();
        res.add(start);
        seen.add(start);
        while (res.size() < map.size()) {
            List<Integer> tmp = map.get(cur);
            for (int i : tmp) {
                if(!seen.contains(i)){
                    res.add(i);
                    cur = i;
                    seen.add(i);
                    break;
                }
            }
        }
        return res;
    }


    /**
     * 一个 string ， 先 remove 它最长的并且为回文的 prefix subString， 然后接着 remove 剩下 String 的最长回文 prefix，
     * 最后返回剩下的
     * @param s
     * @return
     */
    private static String removePalindromePrefix(String s) {
        return "";
    }

    /**
     * https://www.1point3acres.com/bbs/thread-658866-1-1.html 第三题
     */
    private static String[] aligningNewspaper(String[][] lines, String[] aligns, int width) {
        StringBuilder sbb = new StringBuilder();
        for(int i = 0; i < width + 2; i++){
            sbb.append("*");
        }
        String bar = sbb.toString();
        sbb.setLength(0);
        List<String> output = new ArrayList<>();
        output.add(bar);
        for (int i = 0; i < lines.length; ++i) {
            String[] line = lines[i];
            List<StringBuilder> sbs = new ArrayList<>();
            sbs.add(new StringBuilder());
            int curSb = 0;
            sbs.get(curSb).append(line[0]);
            for (int j = 1; j < line.length; ++j) {
                String word = line[j];
                if (sbs.get(curSb).length() + word.length() + 1 <= width) {
                    sbs.get(curSb).append(" ").append(word);
                } else {
                    sbs.add(new StringBuilder());
                    curSb++;
                    sbs.get(curSb).append(word);
                }
            }
            for (StringBuilder sb : sbs) {
                output.add(getLine(sb, aligns[i], width));
            }
        }
        output.add(bar);
        String[] res = new String[output.size()];
        for (int i = 0; i < res.length; ++i) {
            res[i] = output.get(i);
        }
        return res;
    }

    public static String getLine(StringBuilder sb, String pos, int width) {
        int remainingSpace = width - sb.length();
        StringBuilder tmp = new StringBuilder();
        String res = "*";
        for(int i = 0; i < remainingSpace; i++){
            tmp.append(" ");
        }
        if (pos.equals("LEFT")) {
            res += sb.toString() + tmp.toString() + "*";
        } else {
            res += tmp.toString() + sb.toString() + "*";
        }
        return res;
    }

    /**
     * https://leetcode.com/discuss/interview-question/788264/robinhood-oa-new-grad
     * @param n
     * @return
     */
    private static String operateZeroAndOne(int n) {
        char[] a = new char[n];
        Arrays.fill(a,'0');
        TreeSet<Integer> zeroInd = new TreeSet<>();

        for(int i=0;i<n;i++){
            zeroInd.add(i);
        }

        String[] cmds = new String[]{"L","L","C0","L","C3"};

        for(String cmd : cmds ){
            if(cmd.charAt(0)=='L'){
                if(!zeroInd.isEmpty()){
                    int ind =  zeroInd.first();
                    a[ind] = '1';
                    zeroInd.pollFirst();
                }
            } else {
                int ind = Integer.parseInt(cmd.substring(1));
                a[ind] = '0';
                zeroInd.add(ind);
            }
        }
        return new String(a);
    }
}
