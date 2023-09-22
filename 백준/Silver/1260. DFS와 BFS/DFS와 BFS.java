import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int[][] arr;
	static boolean[] visited; // 방문처리 배열
	static Queue<Integer> queue;
	static int N, M, V;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt(); // 정점의 개수
		M = sc.nextInt(); // 간선의 개수
		V = sc.nextInt(); // 탐색 시작 정점 번호
		
		// 인접 행렬로 받는다
		arr = new int[N+1][N+1];
		visited = new boolean[N+1];
		// bfs 에 쓸 큐
		queue = new LinkedList<>();
		
		for (int i=0; i < M; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			arr[a][b] = 1;
			arr[b][a] = 1; // 양방향이니까
		}
		System.out.print(V + " ");
		dfs(V);
		System.out.println();
		visited = new boolean[N+1]; // 다시 초기화?
		bfs(V);
		
	}
	
	public static void dfs(int v) {
		visited[v] = true; // 방문처리
		
		// 갈 수 있는 곳 어디?
		for (int i = 0; i < N+1; i++) {
			if (!visited[i] && arr[v][i] == 1) {
				System.out.print(i + " ");
				dfs(i);
			}
		}
	}
	
	public static void bfs(int v) {
		visited[v] = true;
		queue.add(v);
		
		while (!queue.isEmpty()) {
			int t = queue.poll();
			System.out.print(t + " ");
			
			// poll 된 노드의 자식 노드를 돈다
			for(int i = 0; i < N+1; i++) {
				if (!visited[i] && arr[t][i] == 1) {
					visited[i] = true;
					queue.add(i);
				}
			}
		}
	}
}
