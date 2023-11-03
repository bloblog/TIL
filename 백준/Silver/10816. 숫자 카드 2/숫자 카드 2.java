import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(bf.readLine());
		int[] cards = new int[N];
		
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		
		st = new StringTokenizer(bf.readLine());
		for (int i = 0; i < N; i++) {
			int val = Integer.parseInt(st.nextToken());
			cards[i] = val;
			// min, max 값 차이만큼 카운팅 배열 만들어야 하므로 각 값을 찾아야 함
			if (val < min)
				min = val;
			if (val > max)
				max = val;
			
		}

		int M = Integer.parseInt(bf.readLine());
		st = new StringTokenizer(bf.readLine());
		
		int[] target = new int[M];
		for (int i = 0; i < M; i++) {
			target[i] = Integer.parseInt(st.nextToken());
		}

		// 카운팅 배열과 찾는 카드 배열의 인덱스를 일치시키기 위해,
		// 카운팅 배열의 시작 인덱스 0 으로 한다 (원래 값은 min 값)
		// 실제 값을 찾을 때는 min 값만큼 빼준다
		int[] cnt = new int[max - min + 1]; // 예제에서 10-(-10)+1 = 21

		for (int i = 0; i < N; i++) {
			cnt[cards[i]-min]++;
		}
		
		for (int i = 0; i < M; i++) {
			try {
				bw.write(cnt[target[i]-min] + " ");
			} catch (Exception e) {
				bw.write(0 + " ");
			}
		}
		
		bw.flush();
	}
}
