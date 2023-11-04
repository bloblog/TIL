import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N, M;
	static int[] arr;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		arr = new int[101];
		for (int i = 0; i< N+M; i++) {
			// 단방향 이동만 가능
			arr[sc.nextInt()] = sc.nextInt();
		}
		
		bfs(1);
//		System.out.println(Arrays.toString(cnt));
		System.out.println(cnt[100]);
		
	}
	
	static int[] cnt;
	static void bfs(int v) {
		int[] delta = {1,2,3,4,5,6};
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[101];
		cnt = new int[101];
		
		visited[v] = true;
		queue.add(v);
		
		while (!queue.isEmpty()) {
			int t = queue.poll();
			for (int d = 0; d< 6; d++) {
				
				int np = t + delta[d];
				
				if (np == 100) {
					cnt[np] = cnt[t]+1;
					return;
				}
				
				if (np < 100 && !visited[np] && arr[np] == 0) {
					visited[np] = true;
					queue.add(np);
					cnt[np] = cnt[t]+1;
					
				} else if (np < 100 && !visited[np] && arr[np] != 0 && !visited[arr[np]]) {
					// 뱀이든 사다리든 이동 가능하다면
					visited[np] = true;
					cnt[np] = cnt[t]+1;
					
					np = arr[np]; // 뱀이나 사다리 타고 이동한 위치도 처리해줌
					visited[np] = true;
					queue.add(np);
					cnt[np] = cnt[t]+1;
				}
			}
		}
	}
}
