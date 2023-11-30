import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	
	static int N, M;
	static int T;
	static int[][] arr;
	
	static int[] gram;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		T = sc.nextInt();
		
		arr = new int[N][M];
		gram = new int[2];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int val = sc.nextInt();
				if (val == 2) {
					gram[0] = i;
					gram[1] = j;
				}
				arr[i][j] = val;
			}
		} // 인풋 받기 끝
		
		
		bfs(new int[] {0, 0});
		
		// 벽 피해서 가는 경우와 그램 획득해서 가는 경우 중 짧은 거 출력
		
		// 검 ~ 공주 거리
		int dist = Math.abs(gram[0]-N+1) + Math.abs(gram[1]-M+1);
		
		int answer = 0;
		int INF = Integer.MAX_VALUE;
		
		if (time[N-1][M-1] == 0) {
			time[N-1][M-1] = INF;
		}
		
		if (time[gram[0]][gram[1]] == 0) {
			time[gram[0]][gram[1]] = INF - dist;
		}
		
		answer = Math.min(time[N-1][M-1], time[gram[0]][gram[1]] + dist);
		
		if (answer == INF || answer > T) {
			System.out.println("Fail");
		} else {
			System.out.println(answer);
		}
		
		
	}
	
	static int[][] time;
	static int[] dr = {-1, 0, 1, 0}; // 상 우 하 좌
	static int[] dc = {0, 1, 0, -1};
	static void bfs(int[] st) {
		Queue<int[]> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		time = new int[N][M];
		
		visited[st[0]][st[1]] = true;
		queue.add(st);
		
		while (!queue.isEmpty()) {
			int[] t = queue.poll();
			
			for (int d = 0; d < 4; d++) {
				int nr = t[0] +dr[d];
				int nc = t[1] +dc[d];
				
				if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
				
				if (!visited[nr][nc] && arr[nr][nc] != 1) {
					visited[nr][nc] = true;
					queue.add(new int[] {nr, nc});
					time[nr][nc] = time[t[0]][t[1]] + 1;
				}
			}
		}
		
	}
}
