import java.util.*;

class Solution {
    public int solution(int N, int[][] road, int K) {
        time = new int[N+1];
        Arrays.fill(time, Integer.MAX_VALUE);

        arr = new List[N+1];
        for (int i = 0; i < N+1; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < road.length; i++) {
            int a = road[i][0];
            int b = road[i][1];
            int val = road[i][2];

            arr[a].add(new Node(b, val));
            arr[b].add(new Node(a, val));
        }

        dijk(1, N);

        int cnt = 0;
        for (int i = 1; i < N+1; i++) {
            if (time[i] > K) cnt++;
        }

        return N-cnt;
    }
    
    static int[] time;
    static List<Node>[] arr;
    static void dijk(int st, int N) {
        boolean[] visited = new boolean[N+1];

        time[st] = 0;

        for (int i = 0; i < N-1; i++) {
            int min = Integer.MAX_VALUE;
            int idx = -1;
            for (int j = 0; j < N+1; j++) {
                if (!visited[j] && time[j] < min) {
                    min = time[j];
                    idx = j;
                }
            }

            if (idx == -1) break;
            visited[idx] = true;

            for (Node n : arr[idx]) {
                if (!visited[n.v] && time[n.v] > time[idx] + n.w) {
                    time[n.v] = time[idx] + n.w;
                }
            }

        }


    }
}

class Node {
    int v;
    int w;

    public Node(int v, int w) {
        this.v = v;
        this.w = w;
    }
}
