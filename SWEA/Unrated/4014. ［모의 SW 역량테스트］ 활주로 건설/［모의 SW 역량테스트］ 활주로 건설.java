import java.util.Scanner;

public class Solution {
	static int N, X;
	static int[][] arr;
	static int[][] rArr;
	static int[] tmp;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			X = sc.nextInt();
			int cnt = 0;
			
			arr = new int[N][N];
			rArr = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int val = sc.nextInt();
					arr[i][j] = val;
					rArr[j][i] = val;
				}
			} // 인풋 받기 끝
			
			// 가로 방향 한 줄씩 판단
			tmp = new int[N];
			for (int i = 0; i < N; i++) {
				tmp = arr[i];
				if (check(tmp)) {
					cnt++;
				}
			}
			
			// 세로 방향 한 줄씩 판단
			for (int i = 0; i < N; i++) {
				tmp = rArr[i];
				
				if (check(tmp)) {
					cnt++;
				}
			}
			
			System.out.printf("#%d %d\n", tc, cnt);
			
		}
	}
	
	static boolean check(int[] t) {
		// 타겟 배열에서 활주로 건설 가능한지 체크
		boolean flag = true;
		boolean[] visited = new boolean[N];
		
		loop: for (int i = 0; i < N-1; i++) {
			// 높이 일정한 경우 패스
			if (t[i] == t[i+1]) continue; 
			
			// 높이 차 1 이상인 경우
			if (Math.abs(t[i] - t[i+1]) > 1) {
				flag = false;
				break;
			}
			
			// 높이가 1만큼 다르다면?
			
			// 다음 칸이 더 낮은 경우
			if (t[i] > t[i+1]) {
				// 일단 길이는 확보
				if (i+X < N) {
					int h = t[i+1];
					for (int n = 1; n < X; n++) {
						// 돌면서 높이 일정한지 확인
						if (t[i+1+n] != h) {
							flag = false;
							break loop;
						}
					}
					// 높이 기준까지 통과한 경우 활주로 건설
					for (int n = 1; n <= X; n++) {
						visited[i+n] = true;
					}
					if (i-1 > 0) {
						visited[i-1] = true;
					}
				} else {
					flag = false;
					break;
				}
			}
			
			// 다음 칸이 더 높은 경우
			else {
				// 길이 확보 및 활주로 이미 건설된 곳 없어야 함
				if (i-X+1 >= 0) {
					for (int n = 0; n < X; n++) {
						int h = t[i];
						if (visited[i-n] || t[i-n] != h) {
							flag = false;
							break loop;
						}
					}
					
					// 조건 통과 시 활주로 건설
					for (int n = 0; n < X; n++) {
						visited[i-n] = true;
						t[i-n] = t[i+1];
					}
					
				} else {
					flag = false;
					break loop;
				}
			}
		}
		
		// 마지막 칸 직전까지 돌았기 때문에, 마지막칸 까지 체크
		// 이미 활주로 건설되었으면 ok
		// 직전 칸과 높이 차 없으면 ok
		
		if (!visited[N-1] && (t[N-2] != t[N-1])) {
			flag = false;
		}
		
		return flag;
		
	}
}
