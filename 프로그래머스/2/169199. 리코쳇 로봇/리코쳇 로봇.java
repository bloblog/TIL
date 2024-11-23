import java.util.*;

class Solution {
    public int solution(String[] board) {
        int answer = 0;

        n = board.length;
        m = board[0].length();

        char[][] map = new char[n][m];
        int[] start = new int[2];
        int[] goal = new int[2];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                char c = board[i].charAt(j);
                map[i][j] = c;
                if (c == 'R') start = new int[] {i, j};
                if (c == 'G') goal = new int[] {i, j};
            }
        }

        // goal 주변에 장애물이나 가장자리 없으면 -1 반환
        if (!checkForGoal(goal, map)) return -1;

        // 탐색 시작
        answer = bfs(start, goal, map);

        return answer;
    }
    
    static int bfs(int[] start, int[] goal, char[][] map) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {start[0], start[1], 0});
        int[][] visited = new int[n][m]; // 방문한 방향 개수

        while (!queue.isEmpty()) {
            int[] t = queue.poll();
//            System.out.println("t = " + Arrays.toString(t));

            for (int d = 0; d < 4; d++) {
                int[] next = myNextPos(d, new int[] {t[0], t[1]}, map);
//                System.out.println("next = " + Arrays.toString(next));

                // 진행이 안되는 경우
                if (t[0] == next[0] && t[1] == next[1]) {
                    continue;
                }

                if (next[0] == goal[0] && next[1] == goal[1]) {
                    // goal 에 도달
                    return t[2]+1;
                }

                int nr = next[0];
                int nc = next[1];

                if (visited[nr][nc] < 4) {
                    queue.add(new int[] {nr, nc, t[2]+1});
                    visited[t[0]][t[1]]++;
                }
            }
        }
        return -1;
    }

    static int[] myNextPos(int d, int[] pos, char[][] map) {
        int r = pos[0];
        int c = pos[1];

        while (d == 0 && r >= 0 || d == 1 && c < m || d == 2 && r < n || d == 3 && c >= 0) {
            // d에 따라 가장자리 판별
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (nr < 0 || nr >= n || nc < 0 || nc >= m) {
                return new int[] {r, c};
            }

            if (map[nr][nc] == 'D') {
                return new int[] {r, c};
            }

            r = nr;
            c = nc;
        }

        return new int[] {r, c};
    }

    static int n, m;
    static int[] dr = {-1, 0, 1, 0}; // 상 우 하 좌
    static int[] dc = {0, 1, 0, -1};

    static boolean checkForGoal(int[] goal, char[][] map) {
        // 도달 가능 여부 체크
        for (int d = 0; d < 4; d++) {
            int nr = goal[0] + dr[d];
            int nc = goal[1] + dc[d];

            // 가장자리인 경우
            if (nr < 0 || nr >= n || nc < 0 || nc >= m) return true;

            // 장애물 있는 경우
            if (map[nr][nc] == 'D') return true;
        }

        return false;
    }
}