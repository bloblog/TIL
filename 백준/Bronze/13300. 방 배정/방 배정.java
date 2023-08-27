
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt(); // 학생 수
		int K = sc.nextInt(); // 최대 인원 수
		
		int[][] arr = new int[7][2]; 
		// 각 학년 현황 배열
		// 6학년이 6번 인덱스에 들어가도록 길이 조정
		for (int i = 0; i <N; i++) {
			int s = sc.nextInt();
			int y = sc.nextInt();
			arr[y][s]++;
		}
		
		// arr 돌면서 방 개수를 센다.
		int cnt = 0; // 필요한 방의 최소 개수
		
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 2; j++) {
				if (arr[i][j] % K == 0) {
					cnt += arr[i][j]/K;
				} else {
					cnt += arr[i][j]/K + 1;
				}
			}
		}
		
		System.out.println(cnt);
		
		sc.close();
	}
}
