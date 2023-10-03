import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int[] dp = new int[N+1];
		
		int INF = Integer.MAX_VALUE;
		
		for (int i = 1; i <= N; i++) {
			
			if (i % 2 == 1 && i < 5) {
				dp[i] = INF;
				continue;
			} // 2원과 5원을 사용해서 거슬러줄 수 없다.
			if (i == 2 || i == 5) {
				dp[i] = 1;
				continue;
			} // 2원과 5원 따로 설정
			
			if (i < 5) {
				dp[i] = dp[i-2]+1;
				continue;
			} // 5원 못 쓰는 경우
			
			if (dp[i-2] == INF) {
				dp[i] = dp[i-5]+1;
			}
			
			else if (dp[i-5] == INF) {
				dp[i] = dp[i-2]+1;
			}
			
			else {
				dp[i] = Math.min(dp[i-2]+1, dp[i-5]+1);
			}
			
		}
		
		if (dp[N] == INF) {
			System.out.println(-1);
		} else {
			System.out.println(dp[N]);
		}
		
	}
}
