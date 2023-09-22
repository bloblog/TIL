import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N, K; 
	
	// bfs 구현에 사용할 큐
	static Queue<Integer> queue;
	// 방문체크 배열
	static boolean[] visited;
	// 레벨 배열
	static int[] count; 
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		K = sc.nextInt();
		
		queue = new LinkedList<>();
		visited = new boolean[100001];
		count = new int[100001];
		
		bfs(N);
		System.out.println(count[K]);
		
	}
	
	public static void bfs(int v) {
		queue.add(v);
		visited[v] = true;
		
		while (!queue.isEmpty()) {
			int t = queue.poll();
			// 이동 가능 위치들
			int[] arr = {t+1, t-1, t*2};
			
			if (t == K) break;
			
			for (int d = 0; d < 3; d++) {
				if (arr[d] >= 0 && arr[d] <= 100000 && !visited[arr[d]]) {
					visited[arr[d]] = true;
					queue.add(arr[d]);
					count[arr[d]] = count[t]+1;
				}
			}
		}
	}
}
