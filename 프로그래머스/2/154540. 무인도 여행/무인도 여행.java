import java.util.*;

class Solution {
    public int[] solution(String[] maps) {
        List<Integer> nums = new ArrayList<>();

        int n = maps.length;
        int m = maps[0].length();

        // map 숫자 배열로 변환
        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                char t = maps[i].charAt(j);
                if (t == 'X') {
                    map[i][j] = 0;
                } else {
                    map[i][j] = t-48;
                }
            }
        }

        // 0이 아닌 곳부터 시작해서 dfs
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] > 0) {
                    nums.add(bfs(i, j, map));
                }
            }
        }
        if (nums.size() == 0) {
            return new int[] {-1};
        }

        int[] answer = new int[nums.size()];
        for (int i = 0; i < nums.size(); i++) {
            answer[i] = nums.get(i);
        }

        Arrays.sort(answer);

        return answer;
    }
    
    static int bfs(int i, int j, int[][] map) {
        Queue<int[]> queue = new LinkedList<>();
        int sum = map[i][j];
        queue.add(new int[] {i, j});
        map[i][j] = 0;


        while (!queue.isEmpty()) {
            int[] t = queue.poll();
            // 상 우 하 좌 델타값
            int[] dr = new int[] {-1, 0, 1, 0};
            int[] dc = new int[] {0, 1, 0, -1};
            for (int d = 0; d < 4; d++) {
                int nr = t[0] + dr[d];
                int nc = t[1] + dc[d];

                if (nr < 0 || nr >= map.length || nc < 0 || nc >= map[0].length) {
                    continue;
                }

                if (map[nr][nc] > 0) {
                    sum += map[nr][nc];
                    queue.add(new int[] {nr, nc});
                    map[nr][nc] = 0;
                }
            }
        }
        return sum;
    }
}