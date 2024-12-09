class Solution {
    public int solution(int[][] board, int[][] skill) {
        int n = board.length;
        int m = board[0].length;

        // 누적합 배열
        int[][] diff = new int[n + 1][m + 1];

        for (int[] s : skill) {
            int type = s[0];
            int r1 = s[1];
            int c1 = s[2];
            int r2 = s[3];
            int c2 = s[4];
            int degree = (type == 1 ? -s[5] : s[5]);

            diff[r1][c1] += degree;

            // 범위 벗어나면 무시
            if (c2 + 1 < m) diff[r1][c2 + 1] -= degree;
            if (r2 + 1 < n) diff[r2 + 1][c1] -= degree;
            if (r2 + 1 < n && c2 + 1 < m) diff[r2 + 1][c2 + 1] += degree;
        }

        // 누적합 계산
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < m; j++) {
                diff[i][j] += diff[i][j - 1];
            }
        }

        for (int j = 0; j < m; j++) {
            for (int i = 1; i < n; i++) {
                diff[i][j] += diff[i - 1][j];
            }
        }

        // 파괴되지 않은 건물 카운트
        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] + diff[i][j] > 0) answer++;
            }
        }

        return answer;
    }
}