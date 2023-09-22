import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int F, S, G, U, D;
	static int[] d; // 층 이동 델타값
	static boolean[] visited; // 방문배열
	
	static Queue<int[]> queue; // bfs 구현에 필요한 큐
	static int[] count; // 버튼 누르는 횟수 담을 배열
	static String answer; // 최종 정답
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		F = sc.nextInt(); // 총 건물의 층수
		S = sc.nextInt(); // 현재 강호의 층
		G = sc.nextInt(); // 가고자하는 층
		U = sc.nextInt(); // 몇 칸씩 올라가는지
		D = sc.nextInt(); // 몇 칸씩 내려가는지
		
		d = new int[]{U, -D}; // 델타 정의
		queue = new LinkedList<>();
		visited = new boolean[F+1];
		
		answer = "use the stairs"; // 초기화
		bfs(S, 0);
		System.out.println(answer);
		
	}
	
	public static void bfs(int v, int cnt) {
		queue.add(new int[]{v,cnt});
		visited[v] = true;
		
		while(!queue.isEmpty()) {
			int[] t = queue.poll();
			
			if (t[0] == G) {
				answer = String.valueOf(t[1]);
				break;
			}
			
			for (int i = 0; i < 2; i++) {
				if (t[0]+d[i] > 0 && t[0]+d[i] <= F && !visited[t[0]+d[i]]) {
					queue.add(new int[]{t[0]+d[i], t[1]+1});
					visited[t[0]+d[i]] = true;
				}
			}
		}
	}
}
