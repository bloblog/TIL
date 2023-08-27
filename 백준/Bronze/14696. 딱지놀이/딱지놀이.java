
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt(); // 총 라운드 수
		
		for (int r = 0; r < N; r++) {
			// 한 라운드씩 표현
			int a = sc.nextInt(); // A가 내는 딱지의 그림 개수
			int[] a_arr = new int[a]; // A의 딱지 그림
			for (int i = 0; i < a; i++) {
				a_arr[i] = sc.nextInt();
			}
			// b도 똑같이 인풋 받는다
			int b = sc.nextInt(); 
			int[] b_arr = new int[b];
			for (int i = 0; i < b; i++) {
				b_arr[i] = sc.nextInt();
			}
			
			// 각자 딱지 그림을 카운트배열에 넣어 센다.
			int[] cnt_a = new int[4];
			int[] cnt_b = new int[4];
			
			for (int i = 0; i < a; i++) {
				cnt_a[a_arr[i]-1]++;
			}
			for (int i = 0; i < b; i++) {
				cnt_b[b_arr[i]-1]++;
			}
			
			// 카운트 배열 뒤에서부터 비교
			// 무승부가 아닌 경우 flag 바꾼다.
			int flag = 0;
			for (int i = 3; i >= 0; i--) {
				if (cnt_a[i] > cnt_b[i]) {
					System.out.println('A');
					flag = 1;
					break;
				} else if (cnt_a[i] < cnt_b[i]) {
					System.out.println('B');
					flag = 1;
					break;
				}
			}
			if (flag ==0) {
				System.out.println('D');
			}
		}
		sc.close();
	}
}
