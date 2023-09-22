import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
	static int N;
	static int[][] arr;
	static boolean[][] visited;
	static int cnt; // 각 단지 수 카운트
	static List<Integer> val; // 단지 수 배열
	
	// 방향델타 (상 우 하 좌)
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N  = sc.nextInt();
		arr = new int[N+2][N+2];
		visited = new boolean[N+2][N+2];
		val = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			String s = sc.next();
			for (int j = 0; j < N; j++) {
				arr[i+1][j+1] = Integer.parseInt(String.valueOf(s.charAt(j)));
			}
		} // 인풋 받기 끝
		
		// 시작점 배열 돌면서 찾는다
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				if (!visited[i][j] && arr[i][j] == 1) {
					cnt++; // 시작 단지
					dfs(i, j);
					val.add(cnt); // 한 단지 개수 저장
					cnt = 0; // 다음 단지 넘어갈 때 초기화
				}
			}
		}
		
		System.out.println(val.size());
		Collections.sort(val);
		for (int i = 0; i < val.size(); i++) {
			System.out.println(val.get(i));
		}
	}
	
	static void dfs(int x, int y) {
		
		visited[x][y] = true; // 방문처리
		
		// 사방 돌면서 탐색
		for (int d= 0; d < 4; d++) {
			int nr = x + dr[d];
			int nc = y + dc[d];
			if (arr[nr][nc] == 1 && !visited[nr][nc]) {
				// 진출 가능
				visited[nr][nc] = true;
				cnt++;
				dfs(nr, nc);
			}
		}
	}
}
