import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(String[] maps) {
        int answer = 0;

        int n = maps.length;
        int m = maps[0].length();
        boolean[][] map = new boolean[n][m];

        int[] start = new int[2];
        int[] lever = new int[2];
        int[] exit = new int[2];

        // 인풋 데이터타입 수정
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                char c = maps[i].charAt(j);
                if (c == 'X') {
                    map[i][j] = true;
                } else if (c == 'S') {
                    start = new int[] {i, j};
                } else if (c == 'L') {
                    lever = new int[] {i, j};
                } else if (c == 'E') {
                    exit = new int[] {i, j};
                }
            }
        }

        // 시작 -> 레버
        // 방문 배열
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited[i][j] = map[i][j];
            }
        }

        int a = bfs(n, m, start, lever, visited);
        if (a == -1) return -1;
        else answer += a;

        // 레버 -> 출구
        // map 다시 쓸 일 없으니까 그냥 사용
        int b = bfs(n, m, lever, exit, map);
        if (b == -1) return -1;
        else answer += b;

        return answer;
    }
    
    static int bfs(int n, int m, int[] st, int[] ed, boolean[][] visited) {
        // r, c, 이동시간을 담은 큐
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {st[0], st[1], 0});
        visited[st[0]][st[1]] = true;

        // 상하좌우 델타값
        int[] dr = new int[] {-1, 1, 0, 0};
        int[] dc = new int[] {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            int[] t = queue.poll();

            // 도착한 경우 return
            if (t[0] == ed[0] && t[1] == ed[1]) return t[2];

            for (int d = 0; d < 4; d++) {
                int nr = t[0] + dr[d];
                int nc = t[1] + dc[d];

                if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;

                if (!visited[nr][nc]) {
                    queue.add(new int[] {nr, nc, t[2]+1});
                    visited[nr][nc] = true;
                }
            }
        }

        return -1;
    }
}