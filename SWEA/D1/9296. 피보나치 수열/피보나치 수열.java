import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int[] memo = new int[100000];
		memo[1] = 1;
		
		for (int tc = 1; tc <= 5; tc++) {
			int N = sc.nextInt();
			
			for (int n = 2; n <= N; n++) {
				if (memo[n] == 0) {
					memo[n] = memo[n-1] + memo[n-2];
				}
			}
			System.out.println(memo[N]);
		}
	}
}
