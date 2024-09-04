class Solution {
    int solution(int[][] land) {
        int answer = 0;

        int[][] dp = new int[land.length][4];
        // 첫 줄은 그대로
        for (int i = 0; i < 4; i++) {
            dp[0][i] = land[0][i];
        }

        for (int i = 1; i < land.length; i++) {
            for (int j = 0; j < 4; j++) {
                // i-1행 j열의 최대값 구하기
                int max = 0;
                for (int n = 0; n < 4; n++) {
                    if (n != j && max < dp[i-1][n]) {
                        max = dp[i-1][n];
                    }
                }
                dp[i][j] = max + land[i][j];
            }
        }

        // 최댓값 판별
        for (int i : dp[land.length-1]) {
            if (answer < i) {
                answer = i;
            }
        }

        return answer;
    }
}