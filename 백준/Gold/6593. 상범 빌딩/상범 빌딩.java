import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	// 동 서 남 북 상 하 델타값
	static int[] dl = { 0, 0, 0, 0, -1, 1 };
	static int[] dr = { 1, -1, 0, 0, 0, 0 };
	static int[] dc = { 0, 0, 1, -1, 0, 0 };
	
	static int L, R, C;
	static char[][][] arr;
	
	static int[] start;
	static int[] exit;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		L = sc.nextInt();
		R = sc.nextInt();
		C = sc.nextInt();
		
		while (L != 0 && R != 0 && C != 0) {
			arr = new char[L][R][C];
			start = new int[3];
			exit = new int[3];
			
			for (int i = 0; i < L; i++) {
				for (int j = 0; j < R; j++) {
					String str = sc.next();
					for (int k = 0; k < C; k++) {
						arr[i][j][k] = str.charAt(k);
						if (str.charAt(k) == 'S') {
							// 시작점 받기
							start[0] = i;
							start[1] = j;
							start[2] = k;

						} else if (str.charAt(k) == 'E') {
							// 종료점 받기
							exit[0] = i;
							exit[1] = j;
							exit[2] = k;
						}
					}
				}
			}
			
			bfs(start);
			
			if (time[exit[0]][exit[1]][exit[2]] == 0) {
				System.out.println("Trapped!");
			} else {
				System.out.println("Escaped in " + time[exit[0]][exit[1]][exit[2]] + " minute(s).");
				
			}
			
			// 테케 끝인지 체크
			L = sc.nextInt();
			R = sc.nextInt();
			C = sc.nextInt();
		}
		
		
	}
	
	static int[][][] time;
	static void bfs (int[] start) {
		Queue<int[]> queue = new LinkedList<>();
		boolean[][][] visited = new boolean[L][R][C];
		time = new int[L][R][C];
		
		visited[start[0]][start[1]][start[2]] = true;
		queue.add(start);
		
		while (!queue.isEmpty()) {
			int[] t = queue.poll();
			
			if (t[0] == exit[0] && t[1] == exit[1] && t[2] == exit[2]) {
				return;
			}
			
			for (int d= 0; d < 6; d++) {
				int nl = t[0] + dl[d];
				int nr = t[1] + dr[d];
				int nc = t[2] + dc[d];
				
				if (check(nl, nr, nc) && !visited[nl][nr][nc] && arr[nl][nr][nc] != '#') {
					visited[nl][nr][nc] = true;
					time[nl][nr][nc] = time[t[0]][t[1]][t[2]] +1;
					queue.add(new int[] {nl, nr, nc});
				}
			}
		}
	}
	
	static boolean check (int l, int r, int c) {
		if (l < 0 || l >= L || r < 0 || r >= R || c < 0 || c >= C) {
			return false;
		}
		return true;
	}
}
