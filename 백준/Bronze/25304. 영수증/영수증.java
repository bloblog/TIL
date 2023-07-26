import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int total = sc.nextInt();
		int n = sc.nextInt();
		
		int p[] = new int[n];
		int q[] = new int[n];
		for (int i = 0; i <n; i++) {
			p[i] = sc.nextInt();
			q[i] = sc.nextInt();
		}
		
		int sum = 0;
		for (int i = 0; i <n; i++) {
			sum += p[i] *q[i];
		}
		
		if (sum == total) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}
		
	}
}