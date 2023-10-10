import java.util.Arrays;

class Solution {
    static int[][] arr;
	static boolean[] visited;
	static int[][] copyArr;
	static int cnt;
    
    public int solution(int n, int[][] wires) {
		
		// 인접 리스트 만들기
		arr = new int[n+1][n+1];
		
		for (int i = 0; i < n-1; i++) {
			int a = wires[i][0];
			int b = wires[i][1];
			
			arr[a][b] = 1;
			arr[b][a] = 1;
		}
		
		visited = new boolean[n+1];
		int min = Integer.MAX_VALUE;
		
		for (int i= 0; i< n-1; i++) {
			// 전선 하나를 끊어보자
			
			// 하나만 끊을 거니까 복구 필수
			copyArr = new int[n+1][n+1];
			for (int x = 0; x < n+1; x++) {
				for (int y = 0; y < n+1; y++) {
					copyArr[x][y] = arr[x][y];
				}
			}
			
			int a = wires[i][0];
			int b = wires[i][1];
			
			copyArr[a][b] = 0;
			copyArr[b][a] = 0;
			
			// 각각 전력망의 송전탑 개수 카운트
			int cnt1 = 0; // 1번 전력망
			int cnt2 = 0; // 2번 전력망
			
			// a와 b는 연결이 되어있지 않은 상태이므로
			// 둘을 시작점으로 해서 카운트
			cnt1 = dfs(n, a);
			cnt = 0;
			Arrays.fill(visited, false);
			
			cnt2 = dfs(n, b);
			cnt=0;
			Arrays.fill(visited, false);
			
			// 둘의 개수 차이 구하기
			// 만약 개수 차이가 0이면 바로 min = 0 하고 break
			// 개수 차이 min과 비교
			if (cnt1 - cnt2 == 0) {
				min = 0;
				break;
			}
			
			min = Math.min(min, Math.abs(cnt1 - cnt2));
			
		}
		return min;
	}
	static int dfs(int n, int v) {
		cnt++;
		visited[v] = true;
		
		for (int i = 1; i <= n; i++) {
			if (!visited[i] && copyArr[v][i] == 1) {
				dfs(n, i);
			}
		}
		return cnt;
	}
}
