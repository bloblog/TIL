class Solution {
    static boolean[] visited;
    
    public int solution(int n, int[][] computers) {
        int answer = 0;

        visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i, n, computers);
                answer++; // 한 번 dfs 돌았다 = 네트워크 하나다
            }
        }

        return answer;
    }

    static void dfs(int st, int n, int[][] arr) {
        visited[st] = true;

        for (int i = 0; i < n; i++) {
            if (st != i && !visited[i] && arr[st][i] == 1) {
                // 본인 아님, 방문하지 않음, 연결 되어있는 컴퓨터면 dfs
                dfs(i, n, arr);
            }
        }
    }
}