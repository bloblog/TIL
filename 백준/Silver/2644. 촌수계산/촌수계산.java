import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N, M;
	static int a, b;
	static int[][] arr; // 인접행렬
	static boolean[] visited; // 방문 배열
	static int[] count; // 촌수 배열
	static Queue<Integer> queue;
	
	
	static int cnt = 0; // 촌수
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		arr = new int[N+1][N+1];
		visited = new boolean[N+1];
		count = new int[N+1];
		queue = new LinkedList<>();
		
		a = sc.nextInt();
		b = sc.nextInt(); // 계산해야 하는 대상
		
		M = sc.nextInt(); 
		
		for (int i = 0; i < M; i++) {
			int p = sc.nextInt();
			int ch = sc.nextInt();
			arr[p][ch] = 1;
			arr[ch][p] = 1;
 		} // 인풋 받기 끝
		
		bfs(a);
		int answer = -1;
		if (count[b] != 0) {
			answer = count[b];
		}
		
		System.out.println(answer);
		
	}
	
	public static void bfs(int v) {
		visited[v] = true; // 방문처리
		queue.add(v);
		
		while (!queue.isEmpty()) {
			int t = queue.poll();
			
			for (int i = 0; i < N+1; i++) {
				if (!visited[i] && arr[t][i] == 1) {
					count[i] = count[t]+1;
					visited[i] = true;
					queue.add(i);
				}
			}
		}
	}
}
