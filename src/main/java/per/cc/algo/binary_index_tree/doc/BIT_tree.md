### Explaination
Below img is the mechanism of binary index tree.
If you need further explaination: please visit : [huahua](https://www.youtube.com/watch?v=WbafSgetDDk)

![bit_tree](img/bit_tree.png)

The code of BIT:

```java
public class BinaryIndexTree {
    int[] sums;
    int len = 0;

    BinaryIndexTree(int[] arr) {
        len = arr.length;
        sums = new int[len + 1];
    }

    /**
    * the i is idx in arr, value is arr[i]
    */
    public void update(int i, int value) {
        while (i < sums.length) {
            sums[i] += value;
            i += lowBit(i);
        }
    }

    public int query(int i) {
        int sum = 0;
        while (i > 0) {
            sum += sums[i];
            i -= lowBit(i);
        }
        return sum;
    }

    private static int lowBit(int i) {
        return i & (-i);
    }

}

```