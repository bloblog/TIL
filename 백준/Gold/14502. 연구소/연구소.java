import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	static int N, M;
	static int[][] arr;
	static List<int[]> blank; // 빈칸 배열
	static int[] nums; // 빈칸 idx
	static List<int[]> virus; // 바이러스 배열
	static int max = 0;
	
	static int[] res; // 조합 결과
	static boolean[][] visited; // dfs 방문배열
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		arr = new int[N][M];
		blank = new ArrayList<>();
		virus = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int val = sc.nextInt();
				if (val == 0) {
					blank.add(new int[] {i, j});
				} else if (val == 2) {
					virus.add(new int[] {i, j});
				}
				arr[i][j] = val;
			}
		} // 인풋 받기 끝
		
		// 1. 벽 놓을 자리 고르기
		// 빈칸들 중 3개 선택해서 (조합) arr 배열에 반영
		// 다시 원복 필수
		nums = new int[blank.size()];
		for (int i = 0; i < nums.length; i++) {
			nums[i] = i;
		}
		res = new int[3];
		visited = new boolean[N][M];
		comb(0, 0);
		
		// 2. 벽 세운 상태에서 dfs 돌기
		// 돌면서 바이러스 퍼트리기
		// -> comb 내부에 있음
		
		// 3. 돈 다음 방문 x + 원래 빈칸인 곳만 카운트한다
		// max 변수 갱신한다
		System.out.println(max);
	}
	
	static void comb(int idx, int sIdx) {
		if (sIdx == 3) {
			// 뽑힌 위치에 벽 세움
			for (int i = 0; i < 3; i++) {
				int[] t = blank.get(res[i]);
				arr[t[0]][t[1]] = 1;
			}
			
			// 바이러스 기점으로 돌아
			for (int i = 0; i < virus.size(); i++) {
				int[] v = virus.get(i);
				dfs(v[0], v[1]);
			}
			
            // 안전영역 카운트해
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (arr[i][j] == 0 && !visited[i][j]) {
						cnt++;
					}
				}
			}
			
            // max 갱신
			if (cnt > max) max = cnt;
			
			// 초기화
			// 다시 벽 없애
			for (int i = 0; i < 3; i++) {
				int[] t = blank.get(res[i]);
				arr[t[0]][t[1]] = 0;
			}
			
			visited = new boolean[N][M];
			
			return;
		}
		
		if (idx == blank.size()) {
			return;
		}
		
        // 재귀파트
		res[sIdx] = nums[idx];
		comb(idx+1, sIdx+1);
		comb(idx+1, sIdx);
		
	}
	
	
	static int[] dr = {-1, 0, 1, 0}; // 상우하좌
	static int[] dc = {0, 1, 0, -1};
	static void dfs(int r, int c) {
		visited[r][c] = true;
		
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if (nr>=0 && nr<N && nc>=0 && nc<M && !visited[nr][nc] && arr[nr][nc] == 0) {
				dfs(nr,nc);
			}
		}
	}
}
