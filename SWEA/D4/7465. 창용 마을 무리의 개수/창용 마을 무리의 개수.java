import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	static int[] p;
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int T  = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int cnt = 0;
			
			int N = sc.nextInt(); // 사람의 수
			int M = sc.nextInt(); // 관계 수
			
			p = new int[N+1];
			// make set
			for (int i = 0; i < N+1; i++) {
				p[i] = i;
			}
			
			for (int i = 0; i < M; i++) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				
				p[findSet(b)] = findSet(a);
				
				// 만약 내가 수정될 때, 
				// 나를 대표자로 갖는 모든 집합들의 대표자를 다 바꿔줘야 한다.
				for (int ps = 0; ps < N+1; ps++) {
					if (findSet(ps) == b) {
						p[findSet(ps)] = findSet(a);
					}
				}
			}
			
			// p 원소를 카운트하는 배열 만들기
			int[] count = new int[N+1];
			
			// 맨 앞 제외 (0이니까)
			for (int i = 1; i < N+1; i++) {
				count[p[i]]++;
			}
			
			for (int i = 0; i < N+1; i++) {
				if (count[i] != 0) cnt++;
			}
			
				
			System.out.printf("#%d %d\n", tc, cnt);
		}
	}
	
	public static int findSet(int x) {
		// 대표자 찾는 메서드
		if (x != p[x])
			p[x] = findSet(p[x]);
		return p[x];
	}
}
