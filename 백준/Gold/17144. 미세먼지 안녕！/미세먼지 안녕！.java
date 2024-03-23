import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int n, m;
    static int[][] room;
    static int[][] status;
    static int wind;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        int time = sc.nextInt();

        room = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int val = sc.nextInt();
                room[i][j] = val;
                if (val == -1) {
                    wind = i;
                }
            }
        } // 인풋 받기 끝

        for (int t = 0; t < time; t++) {
            // 먼지 확산
            status = new int[n][m]; // 확산 이후 상태
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    spread(i, j);
                }
            }

            // 청소 시작
            // status 의 값을 돌면서 room 에 반영
            visited = new boolean[n][m];
            // 윗칸 돌풍 시작점 [wind-1, 0]
            up(wind-1);

            // 아랫칸 돌풍 시작점 [wind, 0]
            down(wind);

            // 안 바뀐 애들도 status 값 반영하기
            for (int i= 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (!visited[i][j]) {
                        room[i][j] = status[i][j];
                    }
                }
            }
        }

        // 먼지 양 합계
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (room[i][j] != -1) {
                    sum += room[i][j];
                }
            }
        }

        System.out.println(sum);

    }

    static void down(int r) {
        // 오른쪽 방향
        room[r][1] = 0;
        visited[r][1] = true;
        for (int i = 2; i < m; i++) {
            visited[r][i] = true;
            room[r][i] = status[r][i-1];
        }


        // 아래 방향
        room[r+1][m-1] = status[r][m-1];
        visited[r+1][m-1] = true;
        for (int i = 2; i < n-r; i++) {
            visited[r+i][m-1] = true;
            room[r+i][m-1] = status[r+i-1][m-1];
        }

        // 왼쪽 방향
        room[n-1][m-2] = status[n-1][m-1];
        visited[n-1][m-2] = true;
        for (int i = 3; i < m+1; i++) {
            visited[n-1][m-i] = true;
            room[n-1][m-i] = status[n-1][m-i+1];
        }

        // 위 방향
        room[n-2][0] = status[n-1][0];
        visited[n-2][0] = true;
        for (int i = 3; i < n-r; i++) {
            visited[n-i][0] = true;
            room[n-i][0] = status[n-i+1][0];
        }

    }

    static boolean[][] visited;
    static void up(int r) {
        // 오른쪽 방향
        room[r][1] = 0;
        visited[r][1] = true;
        for (int i = 2; i < m; i++) {
            visited[r][i] = true;
            room[r][i] = status[r][i-1];
        }

        // 위 방향
        room[r-1][m-1] = status[r][m-1];
        visited[r-1][m-1] = true;
        for (int i = 2; i < r+1; i++) {
            visited[r-i][m-1] = true;
            room[r-i][m-1] = status[r-i+1][m-1];
        }

        // 왼쪽 방향
        room[0][m-2] = status[0][m-1];
        visited[0][m-2] = true;
        for (int i = 3; i < m+1; i++) {
            visited[0][m-i] = true;
            room[0][m-i] = status[0][m-i+1];
        }

        // 아래 방향
        room[1][0] = status[0][0];
        visited[1][0] = true;
        for (int i = 2; i < r; i++) {
            visited[i][0] = true;
            room[i][0] = status[i-1][0];
        }
    }

    static int[] dr = {-1, 0, 1, 0}; // 상 우 하 좌
    static int[] dc = {0, 1, 0, -1};
    static void spread(int r, int c) {
        // 퍼질 수 있는 곳 카운트
        int cnt = 0;
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
            if (room[nr][nc] == -1) continue;

            cnt++;

            // 주변 확산
            status[nr][nc] += room[r][c] / 5;
        }

        // 남은 먼지 양 계산
        status[r][c] += room[r][c] - (room[r][c] / 5 * cnt);

    }
}
