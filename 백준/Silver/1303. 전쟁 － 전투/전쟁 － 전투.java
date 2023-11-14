import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static char[][] arr;
	static boolean[][] visited;
	static int N, M;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new char[M][N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			String str = st.nextToken();
			for (int j = 0; j < N; j++) {
				arr[i][j] = str.charAt(j);
			}
		}
		
		visited = new boolean[M][N];
		int sum_b = 0;
		int sum_w = 0;
		for (int i = 0; i < M; i++) {
			for (int j = 0; j <N; j++) {
				if (!visited[i][j]) {
					cnt = 1; // 뭉쳐있는 병사의 수
					dfs(i, j);
					
					if (arr[i][j] == 'B') {
						sum_b += cnt*cnt;
					} else {
						sum_w += cnt*cnt;
					}
				}
			}
		}
		System.out.println(sum_w + " " + sum_b);
	}
	
	static int cnt; // 뭉쳐있는 병사의 수
	static int[] dr = {-1, 0, 1, 0}; 
	static int[] dc = {0, 1, 0, -1}; // 상우하좌
	static void dfs(int r, int c) {
		visited[r][c] = true;
		
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (nr < 0 || nr >= M || nc < 0 || nc >= N) continue;
			
			if (!visited[nr][nc] && arr[r][c] == arr[nr][nc]) {
				cnt++;
				dfs(nr, nc);
			}
		}
	}
}
