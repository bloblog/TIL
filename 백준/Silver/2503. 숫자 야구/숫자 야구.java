import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Num {
	int st;
	int mid;
	int ed;
	int s; // 스트라이크
	int b; // 볼
	
	public Num(int st, int mid, int ed) {
		this.st = st;
		this.mid = mid;
		this.ed = ed;
	}
}

public class Main {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt(); // 질문 횟수
		Num[] arr = new Num[N];
		
		for (int i =0; i < N; i++) {
			String st = sc.next();
			arr[i] = new Num(st.charAt(0)-'0', st.charAt(1)-'0', st.charAt(2)-'0');
			arr[i].s = sc.nextInt();
			arr[i].b = sc.nextInt();
		}
		
		// 123 ~ 987 사이의 수 구하기
		nums = new ArrayList<>();
		for (int i = 1; i < 10; i++) {
			for (int j = 1; j < 10; j++) {
				for (int k = 1; k < 10; k++) {
					if (i != j && j != k && i != k) {
						nums.add(new Num(i, j, k));
					}
				}
			}
		}
		
		// nums 에 들어있는 숫자들과
		// arr 에 들어있는 숫자들의 스트, 볼 개수를 비교해서
		// 다르면 다시 볼 필요 없으니 체크해둔다
		
		done = new boolean[nums.size()];
		
		for (int i =0; i< N; i++) {
			for (int j = 0; j < nums.size(); j++) {
				if (!check(j, arr[i])) {
					done[j] = true;
				}
			}
		}
		
		// 다 돌았는데 done 배열이 false 면 가능성 있는 답임
		int cnt = 0;
		for (int i = 0; i< nums.size(); i++) {
			if (!done[i]) cnt++;
		}
		
		System.out.println(cnt);
		
	}
	
	static List<Num> nums;
	static boolean[] done;
	static boolean check(int a, Num n) {
		
		// 이미 안되는 경우면 바로 나가
		if (done[a]) return false;
			
		Num t = nums.get(a);
		int s = 0;
		int b = 0;
		
		if (t.st == n.st) s++;
		if (t.mid == n.mid) s++;
		if (t.ed == n.ed) s++;
		
		if (t.st == n.mid || t.st == n.ed) b++;
		if (t.mid == n.st || t.mid == n.ed) b++;
		if (t.ed == n.st || t.ed == n.mid) b++;
		
		if (n.s == s && n.b == b) return true;
		return false;
		
	}
}
