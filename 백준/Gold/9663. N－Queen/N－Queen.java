import java.util.Arrays;
import java.util.Scanner;

// 퀸 = 가로 세로 대각선 모두 이동 가능하다
public class Main {
	static int[] queen; // 퀸 배치 1차원으로 설정
	static int[][] arr;
	static int N;
	static int cnt; // 경우의 수
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		arr = new int[N][N];
		
		// 맨 첫 줄에 퀸 놓기
		queen = new int[N];
		nq(0);
		System.out.println(cnt);
		
	}
	
	static void nq(int n) {
		// 기저조건
		// 모든 줄에 다 넣으면
		if (n == N) {
			cnt++;
			return;
		}
		
		// 재귀조건
		for (int i = 1; i <= N; i++) {
			if (check(n, i)) {
				queen[n] = i; // 퀸 놓음
				nq(n+1); // 다음 줄
			}
		}
	}
	
	static boolean check(int r, int c) {
		for (int i = 0; i < r; i++) {
			if (queen[i] == c) {
				return false;
			} // 세로 판단
			
			if (Math.abs(r-i) == Math.abs(queen[i]-c)) {
				// 한 줄 떨어져 있는데 컬럼이 1칸 떨어져 있으면 대각선에 위치한 것
				return false;
			} // 대각선 판단
		}
		return true;
	}
}
