import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] arr;

    // 위부터 시작해서 시계방향으로 8방향
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        } // 인풋 받기 끝

        // 각 칸들의 안전거리 구하고, 최대값 찾기
        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int val = 0;
                if (arr[i][j] != 1) {
                    val = bfs(i, j);
                }
                if (max < val) max = val;
            }
        }

        System.out.println(max);

    }

    // 안전거리 반환하는 메서드
    static int bfs(int r, int c) {
        Queue<int[]> queue = new LinkedList<>();
        int[][] count = new int[N][M];

        count[r][c] = 1;
        queue.add(new int[] {r, c});

        while (!queue.isEmpty()) {
            int[] t = queue.poll();
            for (int d = 0; d < 8; d++) {
                int nr = t[0] + dr[d];
                int nc = t[1] + dc[d];

                if (nr < 0 || nr >= N || nc <0 || nc >= M) continue;

                if (count[nr][nc] == 0) {
                    if (arr[nr][nc] == 1) {
                        return count[t[0]][t[1]];
                    } else {
                        count[nr][nc] = count[t[0]][t[1]] + 1;
                        queue.add(new int[] {nr, nc});
                    }
                }

            }
        }
        return -1;
    }
}
