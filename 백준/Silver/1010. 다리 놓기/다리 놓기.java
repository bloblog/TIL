import java.math.BigInteger;
import java.util.Scanner;

public class Main {
	static BigInteger[] memo;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			long answer = 0;
			
			int N = sc.nextInt(); // 서쪽
			int M = sc.nextInt(); // 동쪽
			
			// 조합 구하기 위해 팩토리얼 값 먼저 구한다
			memo = new BigInteger[M+1];
			memo[0] = BigInteger.valueOf(1);
			fact(M);
			
			// 다리 지을 수 있는 경우의 수
			// == 동쪽 사이트 개수에서 서쪽 사이트 개수 만큼 뽑는 경우의 수
			// == 조합 구하면 됨
			answer = memo[M].divide(memo[N].multiply(memo[M-N])).intValue();
			
			System.out.println(answer);
			
		}
	}
	static BigInteger fact(int num) {
		if (num < 2) {
			memo[num] = BigInteger.valueOf(1);
		}
		if (memo[num]==null) {
			memo[num] = fact(num-1).multiply(BigInteger.valueOf(num));
		}
		return memo[num];
	}
	

}
