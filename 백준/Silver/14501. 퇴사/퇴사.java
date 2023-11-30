import java.util.Scanner;

public class Main {
	static int max = 0;
	static Schedule[] arr;
	static int N;
	static int sum;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		arr = new Schedule[N];
		
		for (int i = 0; i < N; i++) {
			arr[i] = new Schedule(i+1, sc.nextInt(), sc.nextInt());
		} // 인풋 받기 끝
		
		// 시작 가능한 지점 찾기
		for (int i = 0; i < N; i++) {
			// 시작할 수 없음
			if (arr[i].date + arr[i].time > N+1) continue;
			// 시작 가능
			dfs(arr[i], 0);
		}
		
		System.out.println(max);
	}
	
	static void dfs(Schedule sch, int sum) {
		
		if (sch.date +sch.time > N) {
			// 마지막 스케줄은 가능한 경우
			if (sch.date +sch.time == N+1) {
				sum += sch.pay;
			}
			if (sum > max) max = sum;
			return;
		}
		
			
		for (int i = sch.date+sch.time-1; i < N; i++) {
			dfs(arr[i], sum + sch.pay);
		}
		
	}
}

class Schedule {
	int date; // 해당 날짜
	int time; // 걸리는 시간
	int pay;
	
	public Schedule(int date, int time, int pay) {
		this.date = date;
		this.time = time;
		this.pay = pay;
	}
}
