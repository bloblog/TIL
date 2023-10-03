import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class Solution {
	
	static int[][] arr; // 인접배열
	static Queue<Integer> queue; // 연결된 노드들 담을 큐
	static boolean[] visited; // 방문배열
	static int[] level; 
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		for (int tc = 1; tc <= 10; tc++) {
			int max = 0; // 가장 마지막에 연락 받은 사람들의 레벨
			int ans = 0;
			int len = sc.nextInt();
			int start = sc.nextInt(); // 시작점
			
			arr = new int[101][101]; // 1 ~ 100 = 정점 번호
			
			// 간선개수 = len / 2
			for (int i = 0; i < len/2; i++) {
				arr[sc.nextInt()][sc.nextInt()] = 1; // 유방향이니까 한 번만
			} // 인풋 다 받음
			
			queue = new LinkedList<>();
			visited = new boolean[101];
			
			level = new int[101];
			
			bfs(start);
			
			// level을 돌면서 같은 레벨인 숫자들 중 가장 큰 숫자가 max
			for (int i = 0; i < 101; i++) {
				if (level[i] > max) max = level[i];
			}
			for (int i = 0; i < 101; i++) {
				if (level[i] == max) ans = i;
			}
			
			System.out.printf("#%d %d\n", tc, ans);
		}
	}
	
	static void bfs(int v) {
		visited[v] = true;
		queue.add(v);
		
		while (!queue.isEmpty()) {
			int curr = queue.poll();
			
			for (int i = 0; i < 101; i++) {
				if (!visited[i] && arr[curr][i] == 1) {
					visited[i] = true;
					queue.add(i);
					level[i] = level[curr]+1;
				}
			}
		}
	}
}