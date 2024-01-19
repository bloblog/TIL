import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int[][] maps) {
        int answer = 0;
        int n = maps.length;
        int m = maps[0].length;

        // 시작점에서 bfs 출발
        int[][] dist = new int[n][m];
        for (int[] a : dist) {
            Arrays.fill(a, -1);
        }

        bfs(0, 0, n, m, maps, dist);

        return dist[n-1][m-1];
    }
    
    static void bfs(int r, int c, int n, int m, int[][] maps, int[][] dist) {
        int[] dr = new int[] {-1, 0, 1, 0}; // 상 우 하 좌
        int[] dc = new int[] {0, 1, 0, -1}; // 상 우 하 좌

        Queue<int[]> queue = new LinkedList<>();

        dist[r][c] = 1;
        queue.add(new int[]{r, c});

        while (!queue.isEmpty()) {
            int[] t = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nr = t[0] + dr[d];
                int nc = t[1] + dc[d];

                if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;

                if (dist[nr][nc] == -1 && maps[nr][nc] == 1) {
                    dist[nr][nc] = dist[t[0]][t[1]] + 1;
                    queue.add(new int[] {nr, nc});
                }
            }
        }
    }
}