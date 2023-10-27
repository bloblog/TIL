import java.util.Scanner;

public class Main {
	static int M, N;
	static int[][] arr; // 배추밭
	static boolean[][] visited;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			M = sc.nextInt();
			N = sc.nextInt();
			arr = new int[M][N];
			
			int K = sc.nextInt();
			for (int i = 0; i < K; i++) {
				arr[sc.nextInt()][sc.nextInt()] = 1;
			} // 인풋 받기 끝
			
			visited = new boolean[M][N];
			int cnt = 0; // 배추지렁이 수
			// 시작점 찾기
			// 배추 있으면서 방문하지 않은 곳
			for (int i = 0; i < M; i++) {
				for (int j = 0; j < N; j++) {
					if (arr[i][j] == 1 && !visited[i][j]) {
						// dfs 시작
						dfs(i, j);
						cnt++;
					}
				}
			}
			// 정답 출력
			System.out.println(cnt);
		}
	}
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static void dfs(int r, int c) {
		visited[r][c] = true;
		
		for (int d = 0; d< 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if (nr >= 0 && nr < M && nc >= 0 && nc < N && arr[nr][nc] == 1 && !visited[nr][nc]) {
				dfs(nr, nc);
			}
		}
	}
}
