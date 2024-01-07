import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int N, M;
    static int[][] arr;
    static int clean;
    static boolean[][] visited;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        arr = new int[N][M];
        Pos st = new Pos(sc.nextInt(), sc.nextInt(), sc.nextInt());

        for (int i = 0; i< N; i++) {
            for (int j = 0; j < M; j++) {
                arr[i][j] = sc.nextInt();
            }
        } // 인풋 받기 끝

        // 작동 시작
        clean = 0;
        dfs(st);
        System.out.println(clean);
    }

    static int[] dr = {-1, 0, 1, 0}; // 북 동 남 서
    static int[] dc = {0, 1, 0, -1};

    static void dfs(Pos now) {
        
        if (arr[now.r][now.c] == 0) clean++;
        arr[now.r][now.c] = -1; // 방문처리

        for (int i = 0; i < 4; i++) {
            now.setD((now.d+3)%4);

            int nr = now.r + dr[now.d];
            int nc = now.c + dc[now.d];

            if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;

            if (arr[nr][nc] == 0) {
                // 청소되지 않은 빈칸 있는 경우
                dfs(new Pos(nr, nc, now.d));
                return;
            }
        }
        // 사방 탐색 했는데 빈칸 없으면 방향 유지하며 후진
        int back = (now.d + 2) % 4;

        int nr = now.r + dr[back];
        int nc = now.c + dc[back];

        if (!(nr < 0 || nr >= N || nc < 0 || nc >= M) && arr[nr][nc] != 1) {
            // 후진
            dfs(new Pos(nr, nc, now.d));
        }

    }
}

class Pos {
    int r;
    int c;
    int d;

    public Pos(int r, int c, int d) {
        this.r = r;
        this.c = c;
        this.d = d;
    }

    public void setR(int r) {
        this.r = r;
    }

    public void setC(int c) {
        this.c = c;
    }

    public void setD(int d) {
        this.d = d;
    }
}