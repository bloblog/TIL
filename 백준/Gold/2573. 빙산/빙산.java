import java.util.Scanner;

public class Main {
	static int N, M;
	static int[][] arr;
	static boolean[][] visited;
	static int cnt; // 빙산 개수
	static int[][] sea; // 주변 바다의 개수
	
	// 상 우 하 좌 델타값
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		arr = new int[N][M];
		visited = new boolean[N][M];
		sea = new int[N][M];
		
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <M; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		
		int answer = 0; // 분리 안 되는 경우 0을 출력해야 함
		int year = 0;
		boolean flag = false;
		// 모두 녹을 때까지 반복
		while(!flag) {
			// 초기화
			cnt = 0; 
			visited = new boolean[N][M]; 
			
			// 녹은 다음 빙산이 남아있는 곳을 기점으로 덩어리의 수를 센다
			for (int i = 1; i < N-1; i++) {
				for (int j = 1; j < M-1; j++) {
					if (arr[i][j] != 0 && !visited[i][j]) {
						dfs(i, j);
						cnt++;
					}
				}
			}
			if (cnt >= 2) {
				// 두 덩어리 이상으로 분리되는 연도 찾기
				answer = year;
				break;
			}
			// cnt 가 0 이다 = 다 녹았다
			if (cnt ==0 ) flag = true;
			
			melt(arr); // 0년째에는 melt 없기 때문에 맨 뒤로 빼자
			year++;
		}
		
		System.out.println(answer);
	}
	
	public static void dfs(int x, int y) {
		visited[x][y] = true;
		
		// 사방 돌면서 연결된 곳 탐색
		for (int d= 0 ; d < 4; d++) {
			int nr = x+dr[d];
			int nc = y+dc[d];
			if (!visited[nr][nc] && arr[nr][nc] != 0) {
				dfs(nr, nc);
			}
		}
	}
	
	public static void melt(int[][] arr) {
		
		// 각 지점 주변의 바다 수 카운트해서 그만큼 빼기
		// 가장자리는 계산 안 해도 됨
		for (int i = 1; i <N-1; i++) {
			for (int j = 1; j < M-1; j++) {
				int sea_cnt = 0;
				for (int d = 0; d < 4; d++) {
					int nr = i + dr[d];
					int nc = j + dc[d];
					if (arr[nr][nc] == 0) {
						sea_cnt++;
					}
				}
				// 주변 바다의 수 배열에 담기
				sea[i][j] = sea_cnt; 
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <M; j++) {
				if (arr[i][j]-sea[i][j] < 0) arr[i][j] = 0;
				else arr[i][j] -= sea[i][j];
			}
		}
	}
}
