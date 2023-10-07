import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	static long[] memo;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		long k = 0;
		int[] arr = new int[N];
		
		int num = sc.nextInt();
		if (num == 1) {
			k = sc.nextLong();
		} else {
			for (int i = 0; i < N; i++) {
				arr[i] = sc.nextInt();
			}
		} // 인풋 받기
		
		memo = new long[21];
		fact(N);
		
		List<Integer> nums = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			nums.add(i+1);
		}
		
		// k가 주어질 때
		if (num == 1) {
			for (int i = 1; i < N; i++) {
				int idx = (int) ((k-1) / memo[N-i]); // 몫 = 인덱스
				System.out.print(nums.get(idx) + " ");
				nums.remove(idx);
				k -= memo[N-i] * idx;
			}
			System.out.print(nums.get(0));
		}
		
		// 순열이 주어질 때
		else {
			long answer = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < nums.size(); j++) {
					if (arr[i] == nums.get(j)) {
						answer += memo[N-i-1] * j;
						nums.remove(j);
						break;
					}
				}
			}
			answer++;
			System.out.println(answer);
		}
		
	}
	static long fact(int N) {
		if (N < 2) {
			memo[N] = 1;
			return 1;
		}
		
		if (memo[N] == 0) {
			memo[N] = fact(N-1) *N;
		}
		
		return memo[N];
	}
}
