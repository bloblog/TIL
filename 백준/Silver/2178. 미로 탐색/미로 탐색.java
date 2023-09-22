import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	// 미로 배열
	static int[][] arr;
	// 방문 배열
	static boolean[][] visited;
	// 큐
	static Queue<int[]> queue;
	// 인풋값
	static int N, M;
	// 칸 수
	static int cnt = 1;
	// 사방 델타 (상 우 하 좌)
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) {
		// bfs => 최단거리
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt(); // row
		M = sc.nextInt(); // column
		
		arr= new int[N+2][M+2];
		visited = new boolean[N+2][M+2];
		queue = new LinkedList<>();
		
		for (int i = 0; i < N; i++) {
			String s = sc.next();
			for (int j = 0; j < M; j++) {
				arr[i+1][j+1] = Integer.parseInt(String.valueOf(s.charAt(j)));
			}
		}
		

		
		// 시작지점 (1, 1) 끝 지점 (N, M)
		bfs(1, 1);
		System.out.println(cnt);
		
	}
	static void bfs(int x, int y) {
		// 방문 처리
		visited[x][y] = true;
		queue.add(new int[] {x, y});
		// 다음으로 갈 수 있는 곳 탐색
		int[][] score = new int[N+2][M+2];
		score[x][y] = 1;
		while (!queue.isEmpty()) {
//			System.out.println(queue.size());
			int[] pos = queue.poll();
			
			for (int d = 0; d < 4; d++) {
				int nr = pos[0] + dr[d];
				int nc = pos[1] + dc[d];
				
				if (arr[nr][nc] == 1 && !visited[nr][nc]) {
					visited[nr][nc] = true;
					queue.add(new int[] {nr, nc});
					score[nr][nc] = score[pos[0]][pos[1]]+1;
				}
			}
			
		}
		cnt = score[N][M];
	}
}
