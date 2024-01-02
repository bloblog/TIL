import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static Land[][] map; // 섬 정보 배열
    static boolean[][] visited;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1}; // 상 우 하 좌
    static int[][] arr;
    static int[][] dist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        } // 인풋 받기 끝

        // 몇번 섬인지 표시하자
        map = new Land[N][N];
        visited = new boolean[N][N];
        int idx = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && arr[i][j] == 1) {
                    dfs(i, j, idx++);
                }
            }
        }

        // 섬 테두리 기점으로 bfs 돌면서 다른 섬까지의 거리 측정
        // 다른 섬까지 가는 최단거리 중 최솟값을 찾자
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] == 1 && map[i][j].isEdge) {
                    int dist = bfs(i, j);
                    if (dist < min) min = dist;
                }
            }
        }

        System.out.println(min);

    }

    static int bfs(int r, int c) {
        Queue<int[]> queue = new LinkedList<>();
        dist = new int[N][N];

        queue.add(new int[] {r, c});
        dist[r][c] = 1;
        while (!queue.isEmpty()) {
            int[] t = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nr = t[0] + dr[d];
                int nc = t[1] + dc[d];

                if (nr < 0 || nr >= N || nc <0 || nc >= N) continue;

                if (dist[nr][nc] == 0) {
                    queue.add(new int[] {nr, nc});
                    if (arr[nr][nc] == 1 && map[nr][nc].idx != map[r][c].idx) {
                        return dist[t[0]][t[1]] - 1;
                    }
                    dist[nr][nc] = dist[t[0]][t[1]] + 1;
                }
            }
        }
        return -1;
    }
    static void dfs(int r, int c, int idx) {
        // 해당 땅과 연결된 땅 = idx 번째 섬
        visited[r][c] = true;
        map[r][c] = new Land(idx, false);

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (nr < 0 || nr >= N || nc <0 || nc >= N) continue;

            if (arr[nr][nc] == 0) {
                // 섬 테두리인 경우
                map[r][c].setEdge(true);
            }

            if (!visited[nr][nc] && arr[nr][nc] == 1) {
                dfs(nr, nc, idx);
            }
        }
    }
}
class Land {
    int idx;
    boolean isEdge;

    public Land(int idx, boolean isEdge) {
        this.idx = idx;
        this.isEdge = isEdge;
    }

    public void setEdge(boolean edge) {
        isEdge = edge;
    }

    @Override
    public String toString() {
        return "Land{" +
                "idx=" + idx +
                ", isEdge=" + isEdge +
                '}';
    }
}
