import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.valueOf(st.nextToken());
        int m = Integer.valueOf(st.nextToken());

        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j) - 48;
            }
        }

        int dist = bfs(map);

        System.out.println(dist == 0 ? -1 : dist);
    }

    static int bfs(int[][] map) {
        int n = map.length;
        int m = map[0].length;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {0, 0, 0});

        // 벽 부숨 여부에 따라 다르게 거리 배열 구성
        int[][][] dist = new int[n][m][2];
        dist[0][0][0] = 1;

        int[] dr = new int[] {-1, 0, 1, 0}; // 상우하좌
        int[] dc = new int[] {0, 1, 0, -1}; // 상우하좌

        while (!queue.isEmpty()) {
            int[] t = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = t[0] + dr[d];
                int nc = t[1] + dc[d];

                if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;

                // 방문체크
                if (dist[nr][nc][t[2]] > 0) {
                    continue;
                }

                if (map[nr][nc] == 1 && t[2] == 0) {
                    // 벽이고 벽 뚫은 적 없으면
                    queue.add(new int[]{nr, nc, 1});
                    dist[nr][nc][1] = dist[t[0]][t[1]][0] + 1;
                } else if (map[nr][nc] == 0) {
                    queue.add(new int[]{nr, nc, t[2]});
                    dist[nr][nc][t[2]] = dist[t[0]][t[1]][t[2]] + 1;
                }
            }
        }

        if (dist[n-1][m-1][0] == 0 && dist[n-1][m-1][1] == 0) {
            return 0;
        } else if (dist[n-1][m-1][0] == 0) {
            return dist[n-1][m-1][1];
        } else if (dist[n-1][m-1][1] == 0) {
            return dist[n-1][m-1][0];
        } else {
            return Math.min(dist[n-1][m-1][0], dist[n-1][m-1][1]);
        }
    }
}