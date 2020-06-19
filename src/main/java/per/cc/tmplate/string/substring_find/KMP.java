package per.cc.tmplate.string.substring_find;

class KMP {
    String pattern;
    String source;

    public static boolean findPatternInSource(String pattern, String source) {
        int[] prefix = movePrefixTable(fillPrefixTable(pattern));
        prefix[0] = -1;
        int n = source.length();
        int s = 0;
        int p = 0;
        while (s < n) {
            if (p == pattern.length() - 1 && pattern.charAt(p) == source.charAt(s)) {
                System.out.println("found pattern, start with " + (s - p));
                return true;
            }
            if (pattern.charAt(p) == source.charAt(s)) {
                p++;
                s++;
            } else {
                p = prefix[p];
                if (prefix[p] == -1) {
                    s++;
                }
            }
        }
        return false;
    }

    private static int[] movePrefixTable(int[] prefix) {
        int[] res = new int[prefix.length];
        for (int i = 0; i < prefix.length - 1; i++) {
            res[i + 1] = prefix[i];
        }
        res[0] = -1;
        return res;
    }

    private static int[] fillPrefixTable(String pattern) {
        int n = pattern.length();
        int[] prefix = new int[n];
        int len = 0;
        int i = 1;
        while (i < n) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                prefix[i] = len;
                i++;
            } else {
                if (len > 0) {
                    len = prefix[len - 1];
                } else {
                    prefix[i] = len;
                    i++;
                }
            }
        }
        return prefix;
    }

    public static void main(String[] args) {
        // fillPrefixTable("abaccabd");
        boolean res = findPatternInSource("abaccabd", "abadabiabaccabaccabd");
        System.out.println(res);
    }
}