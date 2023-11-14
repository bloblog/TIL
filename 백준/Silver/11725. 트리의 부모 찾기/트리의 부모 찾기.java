import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		List<List<Integer>> arr = new ArrayList<>(); // 인접 배열
		
		for (int i = 0; i < N+1; i++) {
			arr.add(new ArrayList<>());
		} // 초기화
		
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			arr.get(a).add(b);
			arr.get(b).add(a);
		}
		
		// 한 단계씩 내려가면서 (bfs처럼) 각 노드의 부모 노드 찾아 배열에 넣자
		int[] parent = new int[N+1];
		Queue<Integer> queue = new LinkedList<>();
		
		queue.add(1);
		parent[1] = 1;
		
		while (!queue.isEmpty()) {
			int t = queue.poll();
			
			for (int i : arr.get(t)) {
				if (parent[i] == 0) {
					// 아직 부모 노드 못 찾았다면
					parent[i] = t;
					queue.add(i);
				}
			}
		}
		
		for (int i = 2; i <= N; i++) {
			System.out.println(parent[i]);
		}
		
		
		
	}
}
