import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int[][] arr;
    static int N;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        int M = sc.nextInt();

        arr = new int [N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        int[] plan = new int[M];
        for (int i = 0; i < M; i++) {
            plan[i] = sc.nextInt();
        } // 인풋 받기 끝

        String answer = "YES";
        // bfs 돌면서 각 도시끼리 연결되어있는지 확인
        for (int i = 0; i < M-1; i++) {
            if (plan[i] == plan[i+1]) {
                continue;
            }
            if (!bfs(plan[i], plan[i+1])) {
                answer = "NO";
                break;
            }
        }

        System.out.println(answer);
    }

    // 도시 두 곳이 연결되어있는지 확인하는 메서드
    static boolean bfs(int st, int ed) {
        // 도시 번호와 배열 숫자 일치시키기 위해 1 빼줌
        st -= 1;
        ed -= 1;
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N];

        visited[st] = true;
        queue.add(st);

        while (!queue.isEmpty()) {
            int t = queue.poll();
            for (int i = 0; i < N; i++) {
                if (!visited[i] && arr[t][i] == 1) {
                    if (i == ed) {
                        return true;
                    }
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
        return false;
    }
}
