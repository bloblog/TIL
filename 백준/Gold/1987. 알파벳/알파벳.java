import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static char[][] arr;
    static int R, C;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new char[R][C];
        for (int i = 0; i < R; i++) {
            char[] chars = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                arr[i][j] = chars[j];
            }
        }

        max = 0;
        visited = new boolean[R][C];
        apb = new boolean[26]; // 알파벳 방문 여부

        visited[0][0] = true;
        apb[(int)arr[0][0] - 65] = true;

        dfs(0, 0, 1);
        System.out.println(max);

    }

    static boolean[][] visited;
    static boolean[] apb;
    static int max;


    static int[] dr = {-1, 0, 1, 0}; // 상 우 하 좌
    static int[] dc = {0, 1, 0, -1};

    static void dfs(int r, int c, int cnt) {
        if (max < cnt) max = cnt;

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;

            if (!visited[nr][nc] && !apb[(int)arr[nr][nc] - 65]) {
                visited[nr][nc] = true;
                apb[(int)arr[nr][nc] - 65] = true;

                dfs(nr, nc, cnt+1);

                visited[nr][nc] = false;
                apb[(int)arr[nr][nc] - 65] = false;
            }
        }
    }
}
