import java.util.*;

class Solution {
    public int[] solution(String[][] places) {
        int[] answer = new int[5];

        for (int i = 0; i < 5; i++) {
            String[] wait = places[i];
            answer[i] = check(wait);
        }
        return answer;
    }
    
    static int check(String[] wait) {
        // 거리두기를 지키고 있으면 1을, 지키지 않고 있으면 0을 반환

        // 좌석배치도 변환 및 응시자 위치 찾기
        List<int[]> p = new ArrayList<>();
        char[][] map = new char[5][5];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                char c = wait[i].charAt(j);
                map[i][j] = c;
                if (c == 'P') p.add(new int[] {i, j});
            }
        }

        // P 기준으로 맨하탄거리 2 이내인 위치들 찾기
        // 찾은 위치에 다른 P 있으면 0 반환
        for (int i = 0; i < p.size(); i++) {
            int[] st = p.get(i);
            if (!search(st, map)) {
                return 0;
            }
        }

        return 1;
    }

    static boolean search(int[] st, char[][] map) {
        // 탐색하면서 다른 P 만나면 false 반환

        // 큐에는 [r, c, st로부터 거리값]이 들어간다
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {st[0], st[1], 0});
        boolean[][] visited = new boolean[5][5];
        visited[st[0]][st[1]] = true;

        int[] dr = new int[] {-1, 0, 1, 0}; // 상 우 하 좌
        int[] dc = new int[] {0, 1, 0, -1};

        while (!queue.isEmpty()) {
            int[] t = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nr = t[0] + dr[d];
                int nc = t[1] + dc[d];

                if (nr < 0 || nr >= 5 || nc < 0 || nc >= 5) continue;
                if (map[nr][nc] == 'X') continue;
                if (!visited[nr][nc]) {
                    visited[nr][nc] = true;
                    if (map[nr][nc] == 'P') {
                        return false;
                    }
                    if (t[2]+1 < 2) {
                        queue.add(new int[] {nr, nc, t[2]+1});
                    }
                }
            }
        }

        return true;

    }
}