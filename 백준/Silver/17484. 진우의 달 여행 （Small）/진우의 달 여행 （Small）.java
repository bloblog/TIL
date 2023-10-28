import java.util.Scanner;

public class Main {
	
	static int N, M;
	static int[][] arr; // 지구와 달 사이
	static int min = Integer.MAX_VALUE; // 최소 연료
	static int cnt; // 특정 경로에서 드는 연료의 양
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		// 왼쪽 오른쪽 아래 0으로 감쌀거임
		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				arr[i][j] = sc.nextInt();
			}
		}// 인풋 받기 끝
		
		
		// 시작점은 첫번째 줄 중 하나
		for (int i = 0; i < M; i++) {
			cnt = arr[0][i];
			dfs(0, i, -1);
		}
		
		System.out.println(min);
		
	}
	
	static int[] dr = {1, 1, 1};
	static int[] dc = {-1, 0, 1}; // 왼아래 아래 오른아래
	// before = 직전에 갔던 방향
	static void dfs(int r, int c, int before) {
		
		// 그냥 줄 수로 판단
		if (r+1 == N) {
			if (cnt < min) min = cnt;
			return;
		}
		
		for (int d = 0; d < 3; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if (nr >=0 && nr < N && nc >=0 && nc <M && before != d) {
				cnt += arr[nr][nc];
				dfs(nr, nc, d);
				cnt -= arr[nr][nc];
			}
		}
	}
}