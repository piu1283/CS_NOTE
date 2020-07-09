package per.cc.algo.string.encode_decode;

/**
 * This will convert a Integer to String which only contains 4 bytes
 * like 291 -> [8bits, 8bits, 8bits, 8bits] ->
 */
class conversion {

    private String intToString(String s){
        int x = s.length();
        char[] b = new char[4];
        for(int i = 3; i >= 0; i--){
            b[3 - i] = (char) (x >> (i * 8) & 0xFF);
        }
        return new String(b);
    }

    private int stringToInt(String s){
        int res = 0;
        char [] cs = s.toCharArray();
        for(int i = 0; i < 4; i++){
            res = (res << 8) + (int)cs[i];
        }
        return res;
    }
}
