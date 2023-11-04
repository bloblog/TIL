import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N;
	static List<List<Integer>> arr;
	public static void main(String[] args) {
		Scanner sc = new Scanner (System.in);
		
		N = sc.nextInt(); // 회원의 수
		
		arr = new ArrayList<>();
		for (int i = 0; i< N+1; i++) {
			arr.add(new ArrayList<>());
		} // 초기화
		
		int a = sc.nextInt();
		int b = sc.nextInt();
		
		while (a != -1) {
			arr.get(a).add(b);
			arr.get(b).add(a);
			a = sc.nextInt();
			b = sc.nextInt();
		} // 인풋 받기 끝
		
		int[] res = new int[N+1];
		int min = N;
		for (int i = 1; i< N+1; i++) {
			int val = bfs(i);
			res[i] = val;
			if (val < min) min = val;
		}
		
		String answer = "";
		int cnt = 0; 
		for (int i = 1; i < N+1; i++) {
			if (res[i] == min) {
				cnt++;
				answer += String.valueOf(i) + " ";
			}
		}
		
		System.out.printf("%d %d\n", min, cnt);
		System.out.println(answer);
		
		
	}
	
	static int[] depth;
	static int bfs(int v) {
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[N+1];
		depth = new int[N+1];
		int max = 0;
		
		visited[v] = true;
		queue.add(v);
		
		while (!queue.isEmpty()) {
			int t = queue.poll();
			
			for (int i = 1; i < N+1; i++) {
				if (!visited[i] && arr.get(t).contains(i)) {
					visited[i] = true;
					queue.add(i);
					depth[i] = depth[t] + 1;
					max = depth[t] + 1;
				}
			}
		}
		// 가장 큰 depth 가 반환됨
		return max;
		
	}
} 
