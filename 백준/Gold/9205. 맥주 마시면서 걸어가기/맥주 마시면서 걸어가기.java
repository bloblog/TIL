import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N;
	static int[] st;
	static int[][] pos; // st에서 갈 수 있는 좌표
	static boolean[] visited;
	static int[] ed;
	static String flag;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for (int tc= 1; tc <= T; tc++) {
			flag = "sad";
			
			st = new int[2];
			ed = new int[2];
			
			N = sc.nextInt();
			
			// 인풋 받기
			st[0] = sc.nextInt();
			st[1] = sc.nextInt();
			
			pos = new int[N+1][2];
			visited = new boolean[N+1];
			
			// 그냥 end 위치까지 받는다
			for (int i = 0; i < N+1; i++) {
				// 마지막 위치 좌표는 따로 받아서 저장해둔다.
				if (i == N) {
					int end_x = sc.nextInt();
					int end_y = sc.nextInt();
					ed[0] = end_x;
					ed[1] = end_y;
					pos[i] = ed;
					break;
				}
				pos[i][0] = sc.nextInt();
				pos[i][1] = sc.nextInt();
			}
			
			dfs(st);
			
			System.out.println(flag);
		}
		
	}
	public static void dfs(int[] st) {
		
		for (int i =0 ;i < N+1; i++) {
			int[] next = pos[i];
			if (calc(next, st) <= 1000 && !visited[i]) {
				if (next == ed) {
					flag = "happy";
					return;
				}
				visited[i] = true;
				dfs(next);
			}
		}
	}
	
	public static int calc(int[] st, int[] ed) {
		return Math.abs(st[0]-ed[0]) + Math.abs(st[1] - ed[1]);
	}
}
