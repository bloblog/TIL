import java.util.Scanner;

public class Main {
	static int[] nums;
	static int[] res;
	static int N, M;
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		nums = new int[N];
		for (int i = 1; i <= N; i++) {
			nums[i-1] = i;
		}
		
		
		res = new int[M];
		perm(0, 0);
	}
	
	
	static void perm(int st, int idx) {
		if (idx == M) {
			for (int i = 0; i < M; i++) {
				System.out.printf("%d ", res[i]);
			}
			System.out.println();
			return;
		}
		
		for (int i = st; i < N; i++) {
			res[idx] = nums[i];
			perm(st++, idx+1);
		}
	}
	
}
