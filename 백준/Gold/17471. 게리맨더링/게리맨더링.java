import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static boolean[] visited;
	static int cnt;
	static int N;
	static int[][] arr;
	static int[] p;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 구역의 개수 N (1~N)
		N = sc.nextInt();
		
		// 구역의 인구
		int total = 0;
		p = new int[N+1];
		for (int i =1; i <= N; i++) {
			int num = sc.nextInt();
			p[i]= num;
			total+=num;
		}
		
		arr = new int[N+1][N+1];
		// 인접 정보 (무향)
		for (int i = 1; i <= N; i++) {
			// i = 지역 번호
			int repeat = sc.nextInt();
			for (int j = 0; j < repeat; j++) {
				int a = sc.nextInt();
				arr[i][a] = 1;
				arr[a][i] = 1;
			}
		}
		
		int min = Integer.MAX_VALUE;
		visited = new boolean[N];
		// 조합 구하자
		// sel 안의 지역들을 하나의 지역구로
		for (int i = 1; i < (1<<N); i++) {
			if (Integer.bitCount(i) <= N/2) {
				cnt = 0;
				int[] sel = new int[Integer.bitCount(i)];
				int idx = 0;
				for (int j = 0; j < N; j++) {
					if ((i & (1<<j)) > 0) {
						sel[idx++] = j+1;
					}
				}
				
//				System.out.println("선택된 지역구 " + Arrays.toString(sel));
				
				// 선택된 선거구들이 이어져있는지 조사 + 선택되지 않은 지역들을 묶은 선거구도 조사
				if (check(sel)) {
					// 둘 다 이어져있으면 인구 수 합 차의 절대값을 최소값과 비교해서 업데이트
//					System.out.println("res = " + Math.abs(cnt-(total-cnt)));
					if (Math.abs(cnt-(total-cnt)) < min) min = Math.abs(cnt-(total-cnt));
				}
			}
		}
		if (min == Integer.MAX_VALUE) min = -1;
		System.out.println(min);
		
	}
	// 선거구에 해당하지 않는 지역들을 visited 처리해서 못 가게 하고
	// 그 상태에서 연결되어 있는지 체크
	static boolean check(int[] sel) {
		visited = new boolean[N]; // 초기화
		Arrays.fill(visited, true);
		for (int i = 0; i < N; i++) {
			for (int j =0; j < sel.length; j++) {
				visited[sel[j]-1] = false;
			}
		}
		
		// visited 처리 완료
		
		// 원본 백업
		boolean[] copyVisited = visited.clone();
		
		for (int i =0; i < sel.length; i++) {
			cnt = 0;
			// 선택된 지역구 중 하나 기점으로 해서 돈다
			dfs(sel[i], sel);
			// 모든 노드 다 방문했는지 체크 = 다 방문했으면 연결되어있는 것
			for (int j = 0; j < N; j++) {
				if (!visited[j]) {
					return false;
				}
			}
			break;
		}
		
		// 반대의 경우도 조사
		// 초기화
		visited = copyVisited.clone();
		for (int n =1 ; n <= N; n++) {
			boolean flag = false;
			for (int s = 0; s < sel.length; s++) {
				// 반대 선거구의 지역 하나 뽑아서 돌리자
				if (sel[s] == n) flag = true;
			}
			
			if (!flag) {
				dfs2(n, sel);
				break;
			}
		}
		
		// 모든 노드 다 방문했는지 체크 = 반대로 다 false면 연결되어있는 것
		for (int j = 0; j < N; j++) {
			if (visited[j]) {
				return false;
			}
		}
		return true;
		
	}
	
	static void dfs(int v, int[] sel) {
		visited[v-1] = true;
//		System.out.println("인구수 = " + p[v]);
		cnt += p[v];
		
		for (int i = 1; i<= N; i++) {
			if (!visited[i-1] && arr[v][i] == 1) {
				dfs(i, sel);
			}
		}
	}
	
	// 반대의 경우도 조사
	static void dfs2(int v, int[] sel) {
		visited[v-1] = false;
		
		for (int i = 1; i<= N; i++) {
			if (visited[i-1] && arr[v][i] == 1) {
				dfs2(i, sel);
			}
		}
	}
}