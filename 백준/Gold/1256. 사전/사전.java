import java.math.BigInteger;
import java.util.Scanner;

public class Main {
	static BigInteger[] memo;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N= sc.nextInt();
		int M= sc.nextInt();
		int K= sc.nextInt();
		
		String answer = "";
		
		// k가 사전 개수보다 큰지 판별하기
		// 그러려면 팩토리얼 계산 필요
		memo = new BigInteger[201];
		memo[0] = BigInteger.ONE; // 분자에 0이 가는 경우 예외 방지
		
		fact(N+M);
		
		if (memo[N+M].divide(memo[N].multiply(memo[M])).compareTo(BigInteger.valueOf(K)) == -1) {
			answer += "OutOfRange";
		} else {
			// 순열 계산
			int total = N+M;
			for (int i =1; i < total; i++) {
				
				// 해당 자리가 z 자리인 경우
				if (N <= 0) {
					for (int z=0; z < M-1; z++) {
						answer += "z";
					}
					break;
					
				} else if (M <= 0) {
					for (int a=0; a < N-1; a++) {
						answer += "a";
					}
					break;
					
				}
				else if (memo[total-i].divide(memo[N-1].multiply(memo[M])).compareTo(BigInteger.valueOf(K)) == -1) {
					answer += "z";
					int val = memo[total-i].divide(memo[N-1].multiply(memo[M])).intValue();
					K -= val;
					M--;
					
				} else {
					answer += "a";
					N--;
				}
			}
			
			// 마지막 남은 문자 마저 붙이기
			if (N != 0) answer += "a";
			else answer += "z";
		}
		
		// 정답 출력
		if (answer.equals("OutOfRange")) System.out.println(-1);
		else System.out.println(answer);
	}
	
	// 팩토리얼 계산해서 메모이제이션
	static BigInteger fact(int num) {
		
		if (num <2) {
			memo[num] = BigInteger.ONE;
			return BigInteger.ONE;
		}
		
		if (memo[num] == null) {
			memo[num] = fact(num-1).multiply(BigInteger.valueOf(num));
		} 
		
		return memo[num];
	}
}
