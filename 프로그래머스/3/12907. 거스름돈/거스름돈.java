class Solution {
    public int solution(int n, int[] money) {
        long[][] dp = new long[money.length+1][n+1];
        
         // 첫번째 단위만 고려
        for (int i = 1; i < n+1; i++) {
            dp[1][i] += (i % money[0] == 0 ? 1 : 0);
        }

        for (int i = 2; i < money.length+1; i++) {
            int unit = money[i-1];
            for (int j = 1; j < n+1; j++) {
                dp[i][j] += (j == unit ? 1 : 0);
                dp[i][j] += dp[i-1][j];
                if (j >= unit) {
                    dp[i][j] += dp[i][j - unit];
                }
            }
        }

//        for (long[] l : dp) {
//            System.out.println(Arrays.toString(l));
//        }

        return (int) dp[money.length][n] % 1000000007;
    }
}