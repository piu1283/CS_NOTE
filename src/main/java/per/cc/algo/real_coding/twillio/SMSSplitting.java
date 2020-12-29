package per.cc.algo.real_coding.twillio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Input is a string of characters that represents a text message. You need to segment this message into chunks of
 * messages each of length 160 characters and add suffix "(1/5)" (representing pagination) at the end of each
 * segmented message (Length of "(1/5)" is included in 160 length limit).
 *
 * Input: "njdksjfn jdfnds kjfdklsjf jsdofjsd f jdslkjfgdslkngdslkjg fljksdjflsfdsjfdslkfjdslkfmdsklmfgn ljsdglkdsfg
 * d lkjgdslkgjdsljgdslkjgdsfjngds lkjsdlkgjdsgkldsjgsdlkg lkjdslkgjdslkgjdslgmnds glkjgdslkjgdslkjfgodsjfds g,
 * mdsgkjdsngdlsknfgldsjfglkdsjfglkdsjglkdsjglkdsgjdsklgjdslk lkgjdslkgfjdslkgjdslkgjdsljfgdslkgjmdslkg
 * kljghjdslkjgdslkjfg"
 *
 * Output: ['njdksjfn jdfnds kjfdklsjf jsdofjsd f jdslkjfgdslkngdslkjg fljksdjflsfdsjfdslkfjdslkfmdsklmfgn
 * ljsdglkdsfg d lkjgdslkgjdsljgdslkjgdsfjngds (1/3)', 'lkjsdlkgjdsgkldsjgsdlkg lkjdslkgjdslkgjdslgmnds
 * glkjgdslkjgdslkjfgodsjfds g,mdsgkjdsngdlsknfgldsjfglkdsjfglkdsjglkdsjglkdsgjdsklgjdslk (2/3)',
 * 'lkgjdslkgfjdslkgjdslkgjdsljfgdslkgjmdslkg kljghjdslkjgdslkjfg(3/3)']
 *
 * Bonus Points: Pass the large test cases of question 3 without using split() function.
 */
public class SMSSplitting {
    public static void main(String[] args) {
        List<String> res = getSplit("njdksjfn jdfnds kjfdklsjf jsdofjsd f jdslkjfgdslkngdslkjg " +
                "fljksdjflsfdsjfdslkfjdslkfmdsklmfgn ljsdglkdsfg d lkjgdslkgjdsljgdslkjgdsfjngds " +
                "lkjsdlkgjdsgkldsjgsdlkg lkjdslkgjdslkgjdslgmnds glkjgdslkjgdslkjfgodsjfds g," +
                "mdsgkjdsngdlsknfgldsjfglkdsjfglkdsjglkdsjglkdsgjdsklgjdslk lkgjdslkgfjdslkgjdslkgjdsljfgdslkgjmdslkg" +
                " kljghjdslkjgdslkjfg");
        for (String s : res) {
            System.out.println(s);
        }
    }

    private static List<String> getSplit(String msg) {
        if(msg.length() < 160){
            return Collections.singletonList(msg);
        }
        List<String> res = new ArrayList<>();
        int maxLen = 154;
        int start = 0;
        int end = start + maxLen;
        while (end < msg.length()) {
            if(msg.charAt(end) != ' '){
                while(end > start && msg.charAt(end) != ' ' && (end < msg.length() - 1 && msg.charAt(end + 1) != ' ')){
                    end--;
                }
            }
            res.add(msg.substring(start, end + 1));
            start = end + 1;
            end = start + maxLen;
        }
        res.add(msg.substring(start, msg.length()));
        for(int i = 0; i < res.size(); i++){
            String suf = "(" + (i + 1) + "/" + res.size() + ")";
            res.set(i, res.get(i) + suf);
        }
        return res;
    }
}
