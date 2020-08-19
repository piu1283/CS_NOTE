package per.cc.algo.real_coding.amazon;

import per.cc.princeton.tmplate.GabowSCC;

import java.util.ArrayDeque;

/**
 * You have a map that marks the location of a treasure island. Some of the map area has jagged rocks and dangerous
 * reefs. Other areas are safe to sail in. There are other explorers trying to find the treasure. So you must figure
 * out a shortest route to the treasure island.
 * <p>
 * Assume the map area is a two dimensional grid, represented by a matrix of characters. You must start from the
 * top-left corner of the map and can move one block up, down, left or right at a time. The treasure island is marked
 * as X in a block of the matrix. X will not be at the top-left corner. Any block with dangerous rocks or reefs will
 * be marked as D. You must not enter dangerous blocks. You cannot leave the map area. Other areas O are safe to sail
 * in. The top-left corner is always safe. Output the minimum number of steps to get to the treasure.
 * <p>
 * Example:
 * <p>
 * Input:
 * [['O', 'O', 'O', 'O'],
 * ['D', 'O', 'D', 'O'],
 * ['O', 'O', 'O', 'O'],
 * ['X', 'D', 'D', 'O']]
 * <p>
 * Output: 5
 * Explanation: Route is (0, 0), (0, 1), (1, 1), (2, 1), (2, 0), (3, 0) The minimum route takes 5 steps.
 */
public class TreasureIsland {
    public static void main(String[] args) {
        int res = ti(new char[][]{
                {'O', 'X', 'O', 'O'},
                {'D', 'O', 'D', 'O'},
                {'O', 'O', 'O', 'O'},
                {'O', 'D', 'O', 'O'}
        });
        System.out.println(res);
    }

    static int[][] direction = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private static int ti(char[][] graph) {
        boolean[][] seen = new boolean[graph.length][graph[0].length];
        ArrayDeque<int[]> que = new ArrayDeque<>();
        que.add(new int[]{0, 0});
        seen[0][0] = true;
        int step = 0;
        while (!que.isEmpty()) {
            int size = que.size();
            for (int i = 0; i < size; i++) {
                int[] cur = que.removeFirst();
                if (graph[cur[0]][cur[1]] == 'X') {
                    return step;
                }
                for (int[] dir : direction) {
                    int newX = cur[0] + dir[0];
                    int newY = cur[1] + dir[1];
                    if (newX < 0 || newX >= graph.length || newY < 0 || newY >= graph[newX].length || graph[newX][newY] == 'D' || seen[newX][newY]) {
                        continue;
                    }
                    seen[newX][newY] = true;
                    que.addLast(new int[]{newX, newY});
                }
            }
            step++;
        }
        return -1;
    }
}

/**
 * https://leetcode.com/problems/01-matrixM
 *
 * You have a map that marks the locations of treasure islands. Some of the map area has jagged rocks and dangerous
 * reefs. Other areas are safe to sail in. There are other explorers trying to find the treasure. So you must figure
 * out a shortest route to one of the treasure islands.
 *
 * Assume the map area is a two dimensional grid, represented by a matrix of characters. You must start from one of
 * the starting point (marked as S) of the map and can move one block up, down, left or right at a time. The treasure
 * island is marked as X. Any block with dangerous rocks or reefs will be marked as D. You must not enter dangerous
 * blocks. You cannot leave the map area. Other areas O are safe to sail in. Output the minimum number of steps to
 * get to any of the treasure islands.
 *
 * Example:
 *
 * Input:
 * [['S', 'O', 'O', 'S', 'S'],
 *  ['D', 'O', 'D', 'O', 'D'],
 *  ['O', 'O', 'O', 'O', 'X'],
 *  ['X', 'D', 'D', 'O', 'O'],
 *  ['X', 'D', 'D', 'D', 'O']]
 *
 * Output: 3
 * Explanation:
 * You can start from (0,0), (0, 3) or (0, 4). The treasure locations are (2, 4) (3, 0) and (4, 0). Here the shortest
 * route is (0, 3), (1, 3), (2, 3), (2, 4).
 */
class TreasureIsland2{

}
