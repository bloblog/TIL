import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt(); // 학생 수
		int[] arr = new int[N]; // 학생들이 뽑은 수
		for (int i = 0; i <N; i++) {
			arr[i] = sc.nextInt();
		}
		
		int[] line = new int[N]; // 줄 순서 배열
		
		for (int i = 0; i < N; i++) {
			// n+1번 학생이 원래 들어가야 할 자리는 n번 인덱스
			// 하지만 뽑은 숫자만큼 앞으로 가서 선다.
			
			// i-arr[i] 의 뒤쪽 인덱스에 이미 서 있던 학생이 있으면, 
			// 한 칸씩 뒤로 간다.
			// 이 때 값이 충돌하지 않게 뒤에서부터 돈다.
			for (int j =N-2; j >= i-arr[i]; j--) {
				if (line[j] != 0) {
					line[j+1] = line[j];
				}
			}
			// 기존 학생 위치 조정 이후에 위치를 잡아준다.
			line[i-arr[i]] = i+1;
		}
		
		for (int i = 0; i< N; i++) {
			System.out.print(line[i] + " ");
		}
		
		
		sc.close();
	}
}