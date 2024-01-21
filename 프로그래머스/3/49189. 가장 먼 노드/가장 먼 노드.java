import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        int answer = 0;

        List<Integer>[] arr = new List[n+1];

        // 초기화
        for (int i = 0; i < n+1; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < edge.length; i++) {
            int a = edge[i][0];
            int b = edge[i][1];

            // 양방향 간선
            arr[a].add(b);
            arr[b].add(a);
        } // 인풋 받기 끝

        Queue<Integer> queue = new LinkedList<>();
        int[] dist = new int[n+1];

        queue.add(1);
        dist[1] = 1;

        int max = 0;
        while (!queue.isEmpty()) {
            int t = queue.poll();

            for (int v : arr[t]) {
                if (dist[v] == 0) {
                    queue.add(v);
                    dist[v] = dist[t] + 1;
                    max = dist[v];
                }
            }
        }

        // 가장 멀리 떨어진 노드 개수 카운트
        int cnt = 0;
        for (int a : dist) {
            if (a == max) cnt++;
        }

        return cnt;
    }
}