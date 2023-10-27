import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int[][] arr; // 인접행렬
	static int n;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		int m = sc.nextInt();
		
		arr = new int[n+1][n+1]; // 학번 1부터 시작
		for (int i = 0; i < m; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			arr[a][b] = 1; // 연결됨
			arr[b][a] = 1; // 무방향 그래프
		} // 인풋 받기 끝
		
		// 시작점은 상근이 = 1
		// bfs 돌면서 depth 체크
		bfs(1);
		System.out.println(cnt);
		
	}
	
	static int cnt;
	static int[] depth;
	static Queue<Integer> queue;
	static boolean[] visited;
	static void bfs(int v) {
		depth = new int[n+1];
		queue = new LinkedList<>();
		visited = new boolean[n+1];
		
		visited[v] = true;
		queue.add(v);
		depth[v] = 0;
		
		while (!queue.isEmpty()) {
			int t = queue.poll();
			for (int i = 1; i <= n; i++) {
				if (!visited[i] && arr[t][i] == 1) {
					// 연결되어있고 방문하지 않았다면
					visited[i] = true;
					queue.add(i);
					depth[i] = depth[t] +1;
					cnt++;
				}
				// 친구의 친구(depth 2) 이상이면 끝내
				if (depth[t] +1 == 3) {
					return;
				}
				
			}
		}
	}
}
