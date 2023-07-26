import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num1 = sc.nextInt();
		int num2 = sc.nextInt();
		int num3 = sc.nextInt();
		
		int[] nums = {num1, num2, num3};
		int m = 0;
		int max = 0;
		
		if ((num1 == num2) && (num2 == num3)) {
			m = 10000 + num1 * 1000;
			
		} else if ((num1 != num2) && (num2 != num3) && (num1!=num3)) {
			for (int i = 0; i < 3; i++) {
				if (nums[i] > max) {
					max = nums[i];
				}
			}
			m = max * 100;
			
		} else {
			int dup = nums[0];
			for (int i = 1; i < 3; i++) {
				if (nums[i] == dup) {
					break;
				} else {
					dup = nums[i];
				}
				
			}
			m = 1000 + dup *100;
			
		}
		
		System.out.println(m);
	}
}