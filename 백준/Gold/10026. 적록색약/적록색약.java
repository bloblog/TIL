import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int N;
    static int[][] arr;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        arr= new int[N][N];

        for (int i = 0; i < N; i++) {
            String s = sc.next();
            char[] cList = s.toCharArray();
            for (int j = 0; j < cList.length; j++) {
                int val = 0;
                if (cList[j] == 'R') {
                    val = 1;
                } else if (cList[j] == 'G') {
                    val = 2;
                } else {
                    val = 3;
                }
                arr[i][j] = val;
            }
        } // 인풋 받기 끝

        visited = new boolean[N][N];
        visited_2 = new boolean[N][N];
        int cnt = 0;
        int cnt_2 = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    bfs(i, j);
                    cnt++;
                }
                if (!visited_2[i][j]) {
                    bfs_2(i, j);
                    cnt_2++;
                }
            }
        }
        System.out.printf("%d %d", cnt, cnt_2);
    }

    static boolean[][] visited;
    static boolean[][] visited_2;
    static int[] dr = {-1, 0, 1, 0}; // 상 우 하 좌
    static int[] dc = {0, 1, 0, -1};
    static void bfs(int r, int c) {
        Queue<int[]> queue = new LinkedList<>();

        visited[r][c] = true;
        queue.add(new int[] {r, c});

        while (!queue.isEmpty()) {
            int[] p = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = p[0] + dr[d];
                int nc = p[1] + dc[d];

                if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;

                if (!visited[nr][nc] && arr[p[0]][p[1]] == arr[nr][nc]) {
                    queue.add(new int[]{nr, nc});
                    visited[nr][nc] = true;
                }
            }
        }
    }

    static void bfs_2(int r, int c) {
        Queue<int[]> queue = new LinkedList<>();

        visited_2[r][c] = true;
        queue.add(new int[] {r, c});

        while (!queue.isEmpty()) {
            int[] p = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = p[0] + dr[d];
                int nc = p[1] + dc[d];

                if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;

                if (!visited_2[nr][nc] && ((arr[p[0]][p[1]] != 3 && arr[nr][nc] != 3) || (arr[p[0]][p[1]] == arr[nr][nc]))) {
                    queue.add(new int[]{nr, nc});
                    visited_2[nr][nc] = true;
                }
            }
        }
    }
}
