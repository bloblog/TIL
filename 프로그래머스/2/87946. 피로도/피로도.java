class Solution {
    public int solution(int k, int[][] dungeons) {
        answer = -1;
        N = dungeons.length;

        visited = new boolean[N];
        nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = i;
        }

        res = new int[N];
        perm(k, 0, dungeons);

        return answer;
    }
    
    static int N, answer;
    static boolean[] visited;
    static int[] nums, res;

    static void perm(int k, int idx, int[][] dungeons) {
        if (idx == N) {
            count(k, res, dungeons);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                res[i] = nums[idx];
                visited[i] = true;
                perm(k, idx+1, dungeons);
                visited[i] = false;
            }
        }
    }

    static void count(int k, int[] order, int[][] dungeons) {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            int idx = order[i];
            int[] d = dungeons[idx];
            if (d[0] <= k) cnt++;
            k -= d[1];
        }

        // 해당 순서로 작업시 최대값인가?
        if (answer < cnt) answer = cnt;
    }
}