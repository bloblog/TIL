import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {

    static int N, M;
    static boolean[] visited;
    static int[] answer;
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];
        answer = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            graph[S].add(E);
        }
        for (int i = 1; i <= N; i++) {
            visited = new boolean[N + 1];
            BFS(i);
        }
        int maxVal = 0;
        // 정답 배열에서 가장 큰 값을 찾고 해당 값을 가진 인덱스를 출력
        for (int i = 1; i <= N; i++) {
            maxVal = Math.max(maxVal, answer[i]);
        }
        for (int i = 1; i <= N; i++) {
            if (answer[i] == maxVal) {
                System.out.print(i + " ");
            }
        }
    }
    static void BFS(int index){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(index);
        visited[index] = true;
        while (!queue.isEmpty()) {
            int nowNode = queue.poll();
            for (int i : graph[nowNode]) {
                if (!visited[i]) {
                    visited[i] = true;
                    answer[i]++;
                    queue.add(i);
                }
            }
        }
    }
}