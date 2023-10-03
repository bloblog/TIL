import java.util.Scanner;

public class Solution {
	static int[] p;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T  = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			String ans = "";
			
			int N = sc.nextInt();
			int M = sc.nextInt(); // 연산의 개수
			
			p = new int[N+1];
			// make set
			for (int i = 0; i < N+1; i++) {
				p[i] = i;
			}
			
			for (int i = 0; i < M; i++) {
				int calc = sc.nextInt();
				int a = sc.nextInt();
				int b = sc.nextInt();
				
				if (calc == 0) {
					// 합집합 연산
					p[findSet(b)] = findSet(a);
				}
				
				else if (calc == 1) {
					// 같은 집합인지 확인하는 연산
					// 만약 a 와 b가 같은 경우는 바로 1
					if (a == b) ans += "1";
					
					else if (findSet(a) == findSet(b)) {
						ans += "1";
					}
					
					else ans += "0";
				}
			}
			
			
			System.out.printf("#%d %s\n", tc, ans);
		}
	}
	
	public static int findSet(int x) {
		// 대표자 찾는 메서드
		if (x != p[x])
			p[x] = findSet(p[x]);
		return p[x];
	}
}
