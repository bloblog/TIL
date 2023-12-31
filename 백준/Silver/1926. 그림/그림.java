import java.util.Scanner;

public class Main {
    static int[] dr = {-1, 0, 1, 0}; // 상우하좌
    static int[] dc = {0, 1, 0, -1};
    static int n, m;
    static int[][] arr;
    static boolean[][] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        int pic_cnt = 0;
        int max = 0;
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && arr[i][j] == 1) {
                    pic_cnt++;
                    cnt = 1;
                    dfs(i, j);
                    if (cnt > max) max = cnt;
                }
            }
        }

        System.out.println(pic_cnt);
        System.out.println(max);

    }
    static int cnt;
    static void dfs(int r, int c) {
        visited[r][c] = true;

        for (int d = 0; d< 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
            if (!visited[nr][nc] && arr[nr][nc] == 1) {
                cnt++;
                visited[nr][nc] = true;
                dfs(nr, nc);
            }
        }
    }
}
