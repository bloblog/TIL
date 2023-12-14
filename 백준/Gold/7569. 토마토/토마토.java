import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int M, N, H;
    static int[][][] arr;

    // 위 아래 왼 오른 앞 뒤
    static int[] dh = {-1, 1, 0, 0, 0, 0};
    static int[] dr = {0, 0, 0, 0, -1, 1};
    static int[] dc = {0, 0, -1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        arr = new int[H][N][M];
        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                st = new StringTokenizer(br.readLine());
                for (int m = 0; m < M; m++) {
                    arr[h][n][m] = Integer.parseInt(st.nextToken());
                }
            }
        } // 인풋 받기 끝

        // 익은 토마토 기점으로 bfs 돌자
        // 다 익는 최소 일수 구해야 하기 때문~
        int[][][] time = new int[H][N][M];

        Queue<Pos> queue = new LinkedList<>();
        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                for (int m = 0; m < M; m++) {
                    if (arr[h][n][m] == 1) {
                        queue.add(new Pos(h, n, m));
                    }
                }
            }
        }

        while (!queue.isEmpty()) {
            Pos t = queue.poll();

            for (int d = 0; d < 6; d++) {
                int nh = t.h + dh[d];
                int nr = t.n + dr[d];
                int nc = t.m + dc[d];

                if (nh >= 0 && nh < H && nr >= 0 && nr < N && nc >= 0 && nc < M
                        && arr[nh][nr][nc] == 0) {
                    arr[nh][nr][nc] = 1;
                    queue.add(new Pos(nh, nr, nc));
                    // 이미 다른 토마토를 기점으로 bfs 돌아서 도달한 적 있으면
                    // 더 적은 시간을 담아라
                    time[nh][nr][nc] = time[t.h][t.n][t.m] + 1;
                }
            }
        }

        // 도달 여부 및 다 익는 데 며칠 걸리는 지 파악
        int max = 0;
        loop: for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                for (int m = 0; m < M; m++) {
                    if (arr[h][n][m] == 0) {
                        max = -1;
                        break loop;
                    }
                    max = Math.max(max, time[h][n][m]);
                }
            }
        }

        System.out.println(max);
    }
}

class Pos {
    int h;
    int n;
    int m;

    public Pos(int h, int n, int m) {
        this.h = h;
        this.n = n;
        this.m = m;
    }
}
