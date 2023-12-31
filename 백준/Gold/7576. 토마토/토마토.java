import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int M,N;
    static int[][] arr;
    static int[][] visited;
    static int[] dr = {-1, 0, 1, 0}; // 상우하좌
    static int[] dc = {0, 1, 0, -1};
    static Queue<int[]> queue;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        visited = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 익은 토마토 기점으로 bfs 돌자
        // 익은 토마토 위치 다 큐에 넣어
        queue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 1) {
                    queue.add(new int[] {i, j});
                }
            }
        }

        bfs();

//        for (int[] a : visited) {
//            System.out.println(Arrays.toString(a));
//        }

        int answer = 0;
        loop: for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j] == 0 && arr[i][j] == 0) {
                    answer = -1;
                    break loop;
                }
                if (answer < visited[i][j] && arr[i][j] == 0) answer = visited[i][j];
            }
        }

        System.out.println(answer);

    }

    static void bfs() {

        while(!queue.isEmpty()) {
            int[] t = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nr = t[0] + dr[d];
                int nc = t[1] + dc[d];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
                    continue;
                }

                if (visited[nr][nc]==0 && arr[nr][nc] == 0) {
                    queue.add(new int[] {nr, nc});
                    visited[nr][nc] = visited[t[0]][t[1]] + 1;
                    continue;
                }

                // 최소일자 구하는 조건 넣음
                if (visited[nr][nc] > visited[t[0]][t[1]] + 1) {
                    queue.add(new int[] {nr, nc});
                    visited[nr][nc] = visited[t[0]][t[1]] + 1;
                }
            }
        }
    }
}
