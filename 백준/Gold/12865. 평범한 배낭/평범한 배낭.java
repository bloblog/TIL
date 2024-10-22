import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] w = new int[n];
        int[] v = new int[n];

        for (int i = 0; i < n; i++) {
            w[i] = sc.nextInt();
            v[i] = sc.nextInt();
        }

        int[][] dp = new int[n][k+1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                // i = 고려하는 아이템
                // j = 현재 배낭에 담을 수 있는 최대 무게

                // 첫번째 아이템 처리
                if (i == 0) {
                    if (w[i] > j) {
                        dp[i][j] = 0;
                    } else {
                        dp[i][j] = v[i];
                    }
                } else {
                    if (w[i] > j) {
                        dp[i][j] = dp[i-1][j];
                    } else {
                        dp[i][j] = Math.max(dp[i-1][j], v[i] + dp[i-1][j - w[i]]);
                    }
                }
            }
        }

        System.out.println(dp[n-1][k]);
        
    }
}