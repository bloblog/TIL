class Solution {
    public int solution(int[][] board) {
        int answer = 0; // 정사각형 한 변의 길이

        int n = board.length;
        int m = board[0].length;

        int[][] dp = new int[n+1][m+1];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 1) {
                    dp[i+1][j+1] = Math.min(Math.min(dp[i][j], dp[i][j+1]), dp[i+1][j]) + 1;
                    if (dp[i+1][j+1] > answer) {
                        answer = dp[i+1][j+1];
                    }
                }
            }
        }

        return (int)Math.pow(answer, 2);
    }
}