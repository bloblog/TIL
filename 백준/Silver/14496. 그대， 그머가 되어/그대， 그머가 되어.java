import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N, a, b;
	static List<List<Integer>> list;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int a = sc.nextInt(); // 시작문자
		int b = sc.nextInt(); // 목표문자
		
		N = sc.nextInt(); // 전체 문자의 수
		int M = sc.nextInt(); // 문자쌍의 수
		
		list = new ArrayList<>();
		for (int i = 0; i < N+1; i++) {
			list.add(new ArrayList<>());
		}
		
		for (int i = 0; i < M; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			list.get(x).add(y);
			list.get(y).add(x); // 양방향으로 치환 가능
		} // 인풋 받기 끝
		
		bfs(a);
		
		// 결과 출력
		System.out.println(counts[b]);
	}
	
	static int[] counts;
	static void bfs(int v) {
		boolean[] visited = new boolean[N+1];
		Queue<Integer> queue = new LinkedList<>();
		counts = new int[N+1];
		Arrays.fill(counts, -1);
		
		visited[v] = true;
		queue.add(v);
		counts[v] = 0;
		
		while (!queue.isEmpty()) {
			int n = queue.poll();
			List<Integer> t = list.get(n);
			
			if (n == b) {
				return;
			}
			
			for (int i = 0; i < N+1; i++) {
				for (int j =0; j < t.size(); j++) {
					if (!visited[i] && t.get(j) == i) {
						visited[i] = true;
						queue.add(i);
						counts[i] = counts[n] +1;
					}
				}
			}
		}
	}
}
