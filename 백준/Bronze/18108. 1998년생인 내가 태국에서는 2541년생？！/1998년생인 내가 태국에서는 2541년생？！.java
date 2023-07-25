import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int year = sc.nextInt();
		int bc_year = year - 543;
		System.out.println(bc_year);
	}
}