import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str;
		
		str = new StringTokenizer(bf.readLine());
		int K = Integer.parseInt(str.nextToken()); // 현재 랜선 개수
		int N = Integer.parseInt(str.nextToken()); // 필요한 랜선 개수
		
		int[] arr = new int[K];
		int max = 0; // 현재 랜선의 최대값
		
		for (int i = 0; i<K; i++) {
			int val = Integer.parseInt(bf.readLine());
			arr[i] = val;
			if (val > max) max = val;
		}
		
		// 이분탐색 시작
		long st = 0;
		long ed = max;
		
		while (st <= ed) {
			long mid = (st+ed)/2;
			
			// mid = 0 이면 그냥 나가
			if (mid == 0) break;
			
			// mid 길이로 랜선 잘랐을 때 몇 개나 나오는지 계산
			long cnt = 0;
			for (int i = 0; i < K; i++) {
				cnt += arr[i]/mid;
			}
			
			// 랜선 개수 cnt가 목표 개수보다 적다 = 더 짧아져야 한다.
			if (cnt < N) {
				ed = mid-1;
			} else {
				st = mid+1;
			}
		}
		
		System.out.println(ed);
		
		
	}
}
