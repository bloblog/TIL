import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int hr = sc.nextInt();
		int min = sc.nextInt();
		int t = sc.nextInt();
		
		int t_hr = t / 60; 
		int t_min = t % 60; 
		
		hr += t_hr; 
		min += t_min; 
		
		if (min >= 60) {
			hr += min / 60; 
			min %= 60; 
		}
		
		if (hr >= 24) {
			hr -= 24;
		}
		
		System.out.println(hr + " " + min);
		
		
	}
}