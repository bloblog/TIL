import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int[] dr = { 0, 0, -1, 1 }; // 왼 오 위 아래
	static int[] dc = { -1, 1, 0, 0 }; // 왼 오 위 아래
	static char[][] arr;
	static int N, M;
	static int[] coins; // 동전 1 동전 2 위치 한 배열에 넣음

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		arr = new char[N][M];
		coins = new int[4];
		int idx = 0;
		for (int i = 0; i < N; i++) {
			String str = sc.next();
			for (int j = 0; j < M; j++) {
				arr[i][j] = str.charAt(j);
				if (str.charAt(j) == 'o') {
					coins[idx++] = i;
					coins[idx++] = j;
				}
			}
		} // 인풋 받기 끝

		// 걍 냅다 0~3 으로 이뤄진 10자리 순열(중복 가능) 다 구해서
		// 하나만 떨어지는 경우의 버튼 누른 횟수 저장 후 최소값 갱신?
		res = new int[10];
		perm(0);
		if (min == Integer.MAX_VALUE)
			min = -1;
		System.out.println(min);
	}

	static int[] nums = new int[] { 0, 1, 2, 3 };
	static int[] res;

	static void perm(int idx) {
		if (idx == res.length) {
			// res 순서대로 버튼을 눌러보자~
			push(res);
			return;
		}

		for (int i = 0; i < 4; i++) {
			res[idx] = nums[i];
			perm(idx + 1);
		}
	}

	static int r1, r2, c1, c2;
	static int min = Integer.MAX_VALUE;

	static void push(int[] dir) {

		r1 = coins[0];
		c1 = coins[1];
		r2 = coins[2];
		c2 = coins[3];
		boolean flag1, flag2;

		for (int d = 0; d < 10; d++) {
			flag1 = move(r1, c1, dir[d], 1);
			flag2 = move(r2, c2, dir[d], 2);

			if (flag1 != flag2) {
				// 하나만 떨어지면 버튼 누른 횟수 최소값 갱신
				if (d + 1 < min)
					min = d + 1;
				return;
			} else if (flag1 && flag2) {
				// 둘다 떨어지면 그만
				return;
			}
		}
	}

	static boolean move(int r, int c, int d, int coinNum) {
		// 동전 이동 -> 떨어지면 true 반환하는 메서드
		int nr = r + dr[d];
		int nc = c + dc[d];

		if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
			return true;
		} else {
			if (arr[nr][nc] != '#') {
				// 안떨어지고 + 벽도 아닌 경우 동전 위치 수정해
				if (coinNum == 1) {
					r1 = nr;
					c1 = nc;
				} else {
					r2 = nr;
					c2 = nc;
				}
			}
		}
		return false;
	}
}
