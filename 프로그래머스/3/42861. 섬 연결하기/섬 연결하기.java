import java.util.*;

class Solution {
    public int solution(int n, int[][] costs) {
        int answer = 0;
        // 간선을 가중치 기준으로 정렬
        Arrays.sort(costs, (a, b) -> Integer.compare(a[2], b[2]));

        // 유니온-파인드 배열 초기화
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        // 간선 선택
        int edgeCount = 0;
        for (int[] cost : costs) {
            int a = cost[0];
            int b = cost[1];
            int weight = cost[2];

            if (find(parent, a) != find(parent, b)) {
                union(parent, a, b);
                answer += weight;
                edgeCount++;
                // 모든 노드가 연결되면 종료
                if (edgeCount == n - 1) break;
            }
        }

        return answer;
    }

    // 유니온-파인드: 루트 찾기
    static int find(int[] parent, int x) {
        if (parent[x] == x) return x;
        return find(parent, parent[x]);
    }

    // 유니온-파인드: 합치기
    static void union(int[] parent, int a, int b) {
        int rootA = find(parent, a);
        int rootB = find(parent, b);
        if (rootA != rootB) parent[rootB] = rootA;
    }
}