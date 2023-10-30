import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int cnt;
	static int[] arr;
	static int S, N;
	static int[] res;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		S = sc.nextInt();

		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		} // 인풋 받기 끝

		cnt = 0;
		for (int i = 1; i <= N; i++) {
			// 부분수열의 크기
			// 순서 유지 = 조합
			res = new int[i];
			comb(0, 0, i);
		}
		System.out.println(cnt);
	}
	
	
	static void comb(int idx, int sIdx, int len) {
		if (idx == len) {
			// 뽑힌 부분수열의 합 구하기
			int sum = 0;
			for (int i = 0; i < len; i++) {
				sum += res[i];
			}
			// 같으면 +1
			if (sum == S) cnt++;
			return;
		}
		
		if (sIdx == N) return;
		
		res[idx] = arr[sIdx];
		comb(idx+1, sIdx+1, len);
		comb(idx, sIdx+1, len);
		
	}
}
