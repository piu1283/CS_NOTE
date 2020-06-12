package per.cc.util;

/**
 * Created by Chen on 3/14/20.
 */
public class ListNode {
    public int val;
    public ListNode next;
    public ListNode(int x) { val = x; }

    public String toString() {
        ListNode cursor = this;
        StringBuilder sb = new StringBuilder();
        do {
            sb.append(cursor.val + "->");
            cursor = cursor.next;
        } while (cursor != null);
        int length = sb.length();
        if (length > 2) { sb = sb.delete(length-2,length); }
        return sb.toString();
    }
}
