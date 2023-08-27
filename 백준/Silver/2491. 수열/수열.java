
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt(); // 수열의 길이 N
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		} // 인풋 받음
		
		// 증가 카운트 배열, 감소 카운트 배열 각각 만들어서
		// 증가세가 있는 경우 해당 배열 위치에 +1 (누적)
		// 반대도 마찬가지
		// 두 배열 중 가장 큰 값 구해서 +1 하면 된다.
		int[] cnt_ic = new int[N-1]; // 증가
		int[] cnt_dc = new int[N-1]; // 감소
		
		int i_cnt = 0;
		int d_cnt = 0;
		for (int i = 0; i < N-1; i++) {
			if (arr[i] <= arr[i+1]) {
				cnt_ic[i] = ++i_cnt;
			} else {
				i_cnt = 0;
				cnt_ic[i] = i_cnt;
			}
			if (arr[i] >= arr[i+1]) {
				cnt_dc[i] = ++d_cnt;
			} else {
				d_cnt = 0;
				cnt_dc[i] = d_cnt;
			}
		}
		
		int max = 0;
		for (int i = 0; i < N-1; i++) {
			if (cnt_ic[i] > max) {
				max = cnt_ic[i];
			}
			if (cnt_dc[i] > max) {
				max = cnt_dc[i];
			}
		}
		System.out.println(max+1);
		sc.close();
	}
}
