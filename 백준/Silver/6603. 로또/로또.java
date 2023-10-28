import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int[] num; // 집합 S
	static int k;
	static int[] res;
	static boolean[] visited;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		k = sc.nextInt();
		while (k != 0) {
			num = new int[k];
			for (int i=0; i < k; i++) {
				num[i] = sc.nextInt();
			}
			
			res = new int[6];
			visited = new boolean[k];
			comb(0, 0);
			
			System.out.println();
			k = sc.nextInt();
		}
		
	}
	
	static void comb(int idx, int sIdx) {
		if (sIdx == 6) {
			for (int i = 0; i < 6; i++) {
				System.out.printf("%d ", res[i]);
			}
			System.out.println();
			return;
		}
		
		if (idx == k) return;
		
		res[sIdx] = num[idx];
		comb(idx+1, sIdx+1);
		comb(idx+1, sIdx);
		
	}
}
