import java.util.Scanner;

public class Main {
	static int N, M;
	static int[][] arr; // 인접행렬
	static boolean[] visited; // 방문배열
	
	static int cnt; // 컴퓨터의 수

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt(); // 컴퓨터의 수
		M = sc.nextInt(); // 컴퓨터 쌍의 수

		arr = new int[N + 1][N + 1];
		visited = new boolean[N + 1];

		for (int i = 0; i < M; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			arr[a][b] = 1;
			arr[b][a] = 1; // 무방향 연결 표시
		} // 인풋 받기 끝

		dfs(1);
		
		System.out.println(cnt);

	}

	public static void dfs(int v) {
		// 방문체크
		visited[v] = true;

		for (int i = 0; i < N + 1; i++) {
			if (!visited[i] && arr[v][i] == 1) {
				cnt++;
				visited[i] = true;
				dfs(i);
			}
		}
	}
}
