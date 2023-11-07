import java.util.Scanner;

public class Main {
	static int N, cnt, status;
	static int[][] arr;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				arr[i][j] =sc.nextInt();
			}
		}
		
		cnt = 0; // 이동 방법의 수
		dfs(0, 1, 0);
		
		System.out.println(cnt);
	}
	
	static int[] dr = {0, 1, 1}; // 오 대 아
	static int[] dc = {1, 1, 0};
	static void dfs(int r, int c, int dir) {
		// dir = 가로 0 대각선 1 세로 2  
		if (r == N-1 && c== N-1) {
			cnt++;
		}
		
		// 가로 상태 -> 오 대
		// 대각선 상태 -> 오 대 아
		// 세로 상태 -> 대 아
		// 이것들만 체크하도록 조건 만들어서 조절해준다.
		for (int d = (dir == 2 ? 1 : 0); d < (dir == 0 ? 2 : 3); d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (nr < N && nc < N && arr[nr][nc] != 1) { 
				if (d == 1) {
					// 대각선 방향으로 가는 경우 추가 체크
					if (arr[r][nc] == 1 || arr[nr][c] == 1) continue;
				}
				dfs(nr, nc, d);
			}
		}
	}
}
