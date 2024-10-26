
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int r = sc.nextInt();
        int c = sc.nextInt();
        int[] st = new int[] {}; // 지훈 위치
        List<int[]> fire = new ArrayList<>();

        char[][] map = new char[r][c];
        // 벽과 불은 방문 처리해서 지훈이가 못가도록 처리
        boolean[][] visited = new boolean[r][c];

        for (int i = 0; i < r; i++) {
            String str = sc.next();
            for (int j = 0; j < c; j++) {
                char ch = str.charAt(j);
                map[i][j] = ch;
                if (ch == '.') continue;
                if (ch == 'J') {
                    st = new int[] {i, j};
                } else if (ch == 'F') {
                    fire.add(new int[] {i, j});
                }
                visited[i][j] = true;
            }
        } // 인풋 받기 끝

        int answer = bfs(st, fire, map,visited);

        System.out.println(answer == -1 ? "IMPOSSIBLE" : answer);

    }

    static int bfs(int[] st, List<int[]> fire, char[][] map, boolean[][] visited) {
        Queue<int[]> q_f = new LinkedList<>();
        Queue<int[]> q_jh = new LinkedList<>();

        for (int[] f : fire) {
            q_f.add(f);
        }

        q_jh.add(new int[] {st[0], st[1], 1});

        int f_idx = 0; // 이동 시간 증가 판단 위한 인덱스

        while (!q_jh.isEmpty()) {
            int[] t = q_jh.poll();

            if (t[0] == 0 || t[1] == 0 || t[0] == map.length-1 || t[1] == map[0].length-1) {
                // 탈출
                return t[2];
            }

            if (t[2] > f_idx) {
                // 불 퍼뜨림
                setFire(map, visited, q_f);
                f_idx++;
            }

            int[] dr = new int[] {-1, 0, 1, 0};
            int[] dc = new int[] {0, 1, 0, -1};

            for (int d = 0; d < 4; d++) {
                // 지훈 이동
                int nr = t[0] + dr[d];
                int nc = t[1] + dc[d];

                if (nr < 0 || nr >= map.length || nc < 0 || nc >= map[0].length) continue;

                if (!visited[nr][nc]) {
                    visited[nr][nc] = true;
                    q_jh.add(new int[] {nr, nc, t[2]+1});
                }
            }
        }

        return -1;

    }

    static void setFire(char[][] map, boolean[][] visited, Queue<int[]> queue) {
        int[] dr = new int[] {-1, 0, 1, 0};
        int[] dc = new int[] {0, 1, 0, -1};

        int size = queue.size();

        for (int i = 0; i < size; i++) {
            // 현재 불이 있는 곳에서만 진행
            int[] t = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = t[0] + dr[d];
                int nc = t[1] + dc[d];

                if (nr < 0 || nr >= map.length || nc < 0 || nc >= map[0].length) continue;

                if (map[nr][nc] != '#' && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    queue.add(new int[] {nr, nc});
                }
            }
        }
    }
}
