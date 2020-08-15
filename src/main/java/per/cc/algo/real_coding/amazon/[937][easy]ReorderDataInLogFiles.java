package per.cc.algo.real_coding.amazon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * You have an array of logs.  Each log is a space delimited string of words.
 * <p>
 * For each log, the first word in each log is an alphanumeric identifier.  Then, either:
 * <p>
 * Each word after the identifier will consist only of lowercase letters, or;
 * Each word after the identifier will consist only of digits.
 * We will call these two varieties of logs letter-logs and digit-logs.  It is guaranteed that each log has at least
 * one word after its identifier.
 * <p>
 * Reorder the logs so that all of the letter-logs come before any digit-log.  The letter-logs are ordered
 * lexicographically ignoring identifier, with the identifier used in case of ties.  The digit-logs should be put in
 * their original order.
 * <p>
 * Return the final order of the logs.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: logs = ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"]
 * Output: ["let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= logs.length <= 100
 * 3 <= logs[i].length <= 100
 * logs[i] is guaranteed to have an identifier, and a word after the identifier.
 */
class ReorderDataInLogFiles {
    public String[] reorderLogFiles(String[] logs) {
        List<String> letters = new ArrayList<>();
        List<String> nums = new ArrayList<>();
        for (String s : logs) {
            if (Character.isDigit(s.charAt(s.indexOf(" ") + 1))) {
                nums.add(s);
            } else {
                letters.add(s);
            }
        }
        Collections.sort(letters, (s1, s2) -> {
            int idx1 = s1.indexOf(" ");
            int idx2 = s2.indexOf(" ");
            String content1 = s1.substring(idx1 + 1);
            String content2 = s2.substring(idx2 + 1);
            if (content1.equals(content2)) {
                return s1.substring(0, idx1).compareTo(s2.substring(0, idx2));
            } else {
                return content1.compareTo(content2);
            }
        });
        int llen = 0;
        int dlen = 0;
        for (int i = 0; i < logs.length; i++) {
            if (llen < letters.size()) {
                logs[i] = letters.get(llen++);
            } else {
                logs[i] = nums.get(dlen++);
            }
        }
        return logs;
    }
}
