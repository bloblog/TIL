import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    static int N, L, R;
    static int[][] arr;
    static boolean[][] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        L = sc.nextInt();
        R = sc.nextInt();

        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr[i][j] = sc.nextInt();
            }
        } // 인풋 받기 끝

        boolean flag = true;
        int cnt = 0;
        while (flag) {
            cnt++;
            flag = false;
            visited = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    union = new ArrayList<>();
                    sum = 0;
                    if (!visited[i][j]) {
                        dfs(i, j);
                    }

                    // 연합국가 있는 경우만 연합
                    if (union.size() > 1) {
                        flag = true;
                        for (int[] a : union) {
                            arr[a[0]][a[1]] = sum / union.size();
                        }
                    }

                }
            }
        }

        System.out.println(cnt-1);

    }

    static List<int[]> union; // 연합국가 리스트
    static int sum;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static void dfs(int r, int c) {
        union.add(new int[] {r, c});
        sum += arr[r][c];
        visited[r][c] = true;

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;

            int val = Math.abs(arr[nr][nc] - arr[r][c]);
            if (!visited[nr][nc] && L <= val && val <= R) {
                dfs(nr, nc);
            }
        }
    }
}