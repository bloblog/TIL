import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int N, M;
	static int[][] arr; // 기존 M*N 보드
	static int min = Integer.MAX_VALUE; // 최소개수
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			String str = sc.next();
			char[] c = str.toCharArray();
			for (int j = 0; j < M; j++) {
				// 흰 = 0  검 = 1 로 하자
				if (c[j] == 'W') arr[i][j] = 0;
				else arr[i][j] = 1;				
			}
		} // 인풋 받기 끝
		
		// 흰색시작 / 검은색 시작 체스 보드 만들기
		int[][] board1 = new int[8][8];
		int[][] board2 = new int[8][8];
		for (int i = 0; i < 8; i++) Arrays.fill(board2[i], 1);
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (i%2 == 0 && j % 2 == 1) {
					board1[i][j] = 1;
					board2[i][j] = 0;
				} else if (i%2 == 1 && j % 2 == 0) {
					board1[i][j] = 1;
					board2[i][j] = 0;
				}
			}
		}
		
		// M*N 보드에서 부분 선택
		for (int i = 0; i <= N-8; i++) {
			for (int j = 0; j <= M-8; j++) {

				count(i, j, board1);
				count(i, j, board2);
			}
		}
		
		System.out.println(min);
		
	}
	
	// 다시 칠해야 하는 정사각형 개수 카운트하는 메서드
	// board = 체스판
	// x, y = M*N 보드에서 어디를 가져올지 그 시작점
	public static void count(int x, int y, int[][] board) {
		int cnt = 0;
		for (int i = x; i < x+8; i++) {
			for (int j = y; j < y+8; j++) {
				if ((arr[i][j] ^ board[i-x][j-y]) == 0) {
					// 같으면 1 다르면 0
					cnt++;
					// 안될 거 같으면 끝내
					if (cnt > min) return;
				}
			}
		}
		if (cnt < min) min = cnt;
	}
}