import java.util.*;

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        // 인풋 값 그래프화
        List<int[]>[] graph = new List[n+1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < fares.length; i++) {
            int x = fares[i][0];
            int y = fares[i][1];
            int v = fares[i][2];

            graph[x].add(new int[] {y, v});
            graph[y].add(new int[] {x, v});
        }

        int answer = INF;

        // i = 합승택시 하차지점
        for (int i = 1; i <= n; i++) {
            int val = dijk(s, i, graph); // 시작점 -> 햐차지점까지 최소값
            int valA = dijk(i, a, graph); // 하차지점 -> A 까지 최소값
            int valB = dijk(i, b, graph); // 하차지점 -> B 까지 최소값

            int sum = val + valA + valB;
            if (sum < answer) answer = sum;
        }


        return answer;
    }
    
    static int INF = Integer.MAX_VALUE;
    static int dijk(int st, int ed, List<int[]>[] graph) {
        if (st == ed) return 0;

        int N = graph.length;

        int[] dist = new int[N];
        boolean[] visited = new boolean[N];

        Arrays.fill(dist, INF);
        dist[st] = 0;

        for (int i = 1; i < N-1; i++) {
            // 방문 안했으면서 가장 비용 적은 노드 선별
            int min = INF;
            int min_idx = -1;
            for (int j = 1; j < N; j++) {
                if (!visited[j] && dist[j] < min) {
                    min = dist[j];
                    min_idx = j;
                }
            }

            if (min_idx == -1) break;

            // 해당 노드 진출 및 주변 노드 dist 갱신
            visited[min_idx] = true;
            for (int[] nodes : graph[min_idx]) {
                if (!visited[nodes[0]] && dist[nodes[0]] > dist[min_idx] + nodes[1]) {
                    dist[nodes[0]] = dist[min_idx] + nodes[1];
                }
            }
        }

        return dist[ed];
    }
}