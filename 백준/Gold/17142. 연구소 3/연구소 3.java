import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	
	static int N, M;
	static int[][] arr;
	static List<int[]> pos; // 바이러스의 위치
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		pos = new ArrayList<>();
		arr = new int[N+2][N+2];
		
		for (int i = 0; i < N+2; i++) {
			for (int j = 0; j < N+2; j++) {
				// 범위 체크 안하려고  테두리 벽으로 채움
				if (i == 0 || i == N+1 || j == 0 || j == N+1) {
					arr[i][j] = 1;
				} else {
					int val = sc.nextInt();
					if (val == 2) {
						pos.add(new int[]{i, j});
					}
					arr[i][j] = val;
				}
			}
		} // 인풋 받기 끝
		
		// 기존 바이러스들 중 활성 바이러스로 만들 것 선택 + bfs 돌리기
		sel = new int[M];
		comb(0, 0);
		
		if (min == Integer.MAX_VALUE) {
			min = -1;
		}

		System.out.println(min);
	}
	
	static boolean[][] visited;
	static Queue<int[]> queue;
	static int[] dr = {-1, 0, 1, 0}; // 상우하좌
	static int[] dc = {0, 1, 0, -1}; // 상우하좌
	static int[][] time; // 도달 시간 배열
	
	
	static void bfs(int[] st) {
		visited = new boolean[N+2][N+2];
		
		int r = st[0];
		int c = st[1];
		
		time[r][c] = 0;
		queue = new LinkedList<>();
		visited[r][c] = true;
		queue.add(st);
		
		while (!queue.isEmpty()) {
			int[] t = queue.poll();
			for (int d = 0; d < 4; d++) {
				int nr = t[0] + dr[d];
				int nc = t[1] + dc[d];
				if (!visited[nr][nc] && arr[nr][nc] != 1) {
					visited[nr][nc] = true;
					queue.add(new int[] {nr, nc});
					if (time[nr][nc] == 0) {
						time[nr][nc] = time[t[0]][t[1]] +1;
					} else {
						// 만약 도달 시간이 이미 들어있다면, 더 작은 값이 들어가라
						time[nr][nc] = Math.min(time[nr][nc], time[t[0]][t[1]] +1);
					}
				}
			}
		}
	}
	
	static int[] sel;
	static void comb(int idx, int st) {
		// M개만 뽑아 = 기저조건
		if (idx == M) {
			// 뽑힌 바이러스들을 기점으로 해서 도달 시간 배열에 넣기
			time = new int[N+2][N+2];
			for (int i = 0; i < M; i++) {
				bfs(pos.get(sel[i]));
			}
			
			// 다 도달하지 못했다면 min = -1
			for (int i = 0; i < N+2; i++) {
				for (int j = 0; j < N+2; j++) {
					if (arr[i][j] == 0 && time[i][j] == 0) {
						return;
					}
				}
			}
			
			// 뽑힌 바이러스 다 돌았을 때의 결과에서 time의 최댓값을 각각 구한다
			int max = 0;
			for (int i = 0; i < N+2; i++) {
				for (int j = 0; j < N+2; j++) {
					// 이 때, 빈칸이었던 것들 중에서만 고른다.
					if (time[i][j] > max && arr[i][j] == 0) max = time[i][j];
				}
			}
			
			// 최댓값 중에서의 최소값을 구한다 = 정답
			if (min > max) min = max;
			return;
		}
		
		// 재귀조건
		for (int i = st; i < pos.size(); i++) {
			sel[idx] = i;
			comb(idx+1, i+1);
		}
	}
}