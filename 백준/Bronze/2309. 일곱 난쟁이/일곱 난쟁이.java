
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int[] height = new int[9];
		int[] fake = new int[2]; // 가짜 난쟁이
		for (int i = 0; i <9; i++) {
			height[i] = sc.nextInt();
		} // 인풋 받기 끝
		
		// 합이 100 인 7명의 난쟁이 키의 조합을 출력해야 한다.
		// 1. 9명의 키 합이 100에서 얼마나 차이가 나는지 val 계산
		// 2. 2명을 뽑았을 때 키의 합이 val인 조합을 찾는다.
		// 3. 그 i와 j를 가짜 난쟁이 배열 에 넣는다
		// 4. 나머지 정렬 후 출력
		
		int sum = 0;
		for (int i = 0; i < 9; i++) {
			sum += height[i];
		}
		
		int val = sum - 100;
		int idx = 0;
		for (int i =0; i < 8; i++) {
			for (int j = i+1; j < 9; j++) {
				if (height[i] + height[j] == val) {
					fake[0] = height[i];
					fake[1] = height[j];
				} 
			}
		}
		
		Arrays.sort(height);
		
		for (int i = 0; i < 9; i++) {
			if (height[i] != fake[0] && height[i] != fake[1]) {
				System.out.println(height[i]);
			}
		}
	}
}
