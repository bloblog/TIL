import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] arr;
    static int cnt, sum, max;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr= new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        } // 인풋 받기 끝

        max = 0;
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                dfs(i, j, arr[i][j], 1);
                visited[i][j] = false;

            }
        }

        System.out.println(max);

    }

    static boolean[][] visited;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1}; // 상 우 하 좌
    static void dfs(int r, int c, int sum, int cnt) {

        if (cnt == 4) {
            // 테트로미노 완성
            if (sum > max) max = sum;
            return;
        }

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;

            if (!visited[nr][nc]) {
                // ㅜ 모양은 2번째 칸에서 탐색 한 번 더 들어간다
                if (cnt == 2) {
                    visited[nr][nc] = true;
                    dfs(r, c, sum + arr[nr][nc], cnt+1);
                    visited[nr][nc] = false;
                }

                visited[nr][nc] = true;
                dfs(nr, nc, sum + arr[nr][nc], cnt+1);
                visited[nr][nc] = false;

            }
        }


    }
}