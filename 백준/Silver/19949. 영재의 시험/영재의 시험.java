import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int[] nums;
	static int[] res;
	static boolean[] visited;
	static int[] answer;
	static int cnt; // 경우의 수
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		answer = new int[10];
		for (int i = 0; i < 10; i++) {
			answer[i] = sc.nextInt();
		}
		
		// 순열로 냅다 구하기
		// 조건 : 3개 연속 안됨, 1~5 중 선택, 10개 선택
		nums = new int[5];
		for (int i = 0; i < 5; i++) {
			nums[i] = i+1;
		}
		
		res = new int[10];
		visited = new boolean[5];
		
		perm(0);
		
		System.out.println(cnt);
	}
	
	// 중복순열
	static void perm(int idx) {
		if (idx == 10) {
			// 정답이랑 비교해서 5점 이상인 경우 +1
			if (check(answer, res) >= 5) {
				cnt++;
			}
			return;
		}
		
		for (int i = 0; i < 5; i++) {
			if (idx >= 2 && res[idx-1] == res[idx-2] && res[idx-2] == nums[i]) {
				continue;
			}
			res[idx] = nums[i];
			perm(idx+1);
		}
	}
	
	static int check(int[] answer, int[] res) {
		int score = 0;
		for (int i = 0; i < 10; i++) {
			if (answer[i] != res[i]) {
				continue;
			}
			score++;
		}
		return score;
	}
}
