import java.util.*;

class Solution {
    public int solution(int[][] board) {
        int[] dr = new int[]{-1, 0, 1, 0}; // 상 우 하 좌
        int[] dc = new int[]{0, 1, 0, -1};
        int n = board.length;
        int INF = Integer.MAX_VALUE;

        // 3차원 비용 배열 (행, 열, 방향)
        int[][][] cost = new int[n][n][4];
        for (int[][] a : cost) {
            for (int[] b : a) {
                Arrays.fill(b, INF);
            }
        }

        // 시작점 초기화 (모든 방향 비용 초기화)
        for (int i = 0; i < 4; i++) {
            cost[0][0][i] = 0;
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0, -1, 0}); // r, c, d, 건설비용

        while (!queue.isEmpty()) {
            int[] t = queue.poll();
            int r = t[0], c = t[1], prevDir = t[2], currCost = t[3];

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
                if (board[nr][nc] != 1) {
                    // 비용 계산
                    int newCost = currCost + (prevDir == -1 || prevDir == d ? 100 : 600);

                    // 최소 비용 갱신 (방향 포함)
                    if (cost[nr][nc][d] > newCost) {
                        cost[nr][nc][d] = newCost;
                        queue.add(new int[]{nr, nc, d, newCost});
                    }
                }
            }
        }

        // 마지막 도착점에서 최소 비용 반환
        int answer = INF;
        for (int i = 0; i < 4; i++) {
            answer = Math.min(answer, cost[n-1][n-1][i]);
        }
        return answer;

    }
}