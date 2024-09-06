import java.util.LinkedList;
import java.util.Queue;
import java.util.Arrays;


class Solution {
    public int solution(int x, int y, int n) {
        if (x == y) return 0;
        bfs(x, y, n);
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
    
    static int answer = Integer.MAX_VALUE;

    static void bfs(int x, int y, int n) {
        Queue<int[]> queue = new LinkedList<>();
        int[] visited = new int[1000001];
        Arrays.fill(visited, Integer.MAX_VALUE);

        // 큐에 들어가는 요소는 (cnt, y값)
        queue.add(new int[] {0, y});
        visited[y] = 0;

        while (!queue.isEmpty()) {
            int[] t = queue.poll();

            if (t[1] < x) continue;

            // 방문체크
            if (t[0] > visited[t[1]]) continue;
            visited[t[1]] = t[0];

            if (t[1] == x && answer > t[0]) {
                answer = t[0];
                break;
            }

            for (int i = 0; i < 3; i++) {
                if (i == 0 && t[1] % 3 == 0) {
                    queue.add(new int[] {t[0]+1, t[1] / 3});
                } else if (i == 1 && t[1] % 2 == 0) {
                    queue.add(new int[] {t[0]+1, t[1] / 2});
                } else {
                    queue.add(new int[] {t[0]+1, t[1] - n});
                }
            }
        }
    }
}