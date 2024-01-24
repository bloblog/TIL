import java.util.Arrays;

class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int[][] arr = new int[n][m];

        // 첫행, 첫열은 최단경로 개수 1
        for (int[] a : arr) Arrays.fill(a,1);

        // 첫번째 행과 열에 웅덩이가 있는 경우에는
        // 웅덩이 같은 줄 친구들은 이제 다 못가
        int rMin = n;
        int cMin = m;
        for (int i = 0; i < puddles.length; i++) {
            int[] p = puddles[i];
            if (p[1] == 1) {
                cMin = Math.min(p[0]-1, cMin);
            }
            if (p[0] == 1) {
                rMin = Math.min(p[1]-1, rMin);
            }
        }

        for (int i = 0; i < puddles.length; i++) {
            int[] p = puddles[i];
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < m; c++) {
                    // 웅덩이면 못 지나가
                    if (r==p[1]-1 && c==p[0]-1) {
                        arr[r][c] = 0;
                    }
                    if (r==0 && c > cMin) arr[r][c] = 0;
                    if (c==0 && r > rMin) arr[r][c] = 0;
                }
            }
        }

        // dp 시작
        // 위, 왼쪽의 최단경로 수의 합 = 현재 위치까지 가는 데 최단경로 수
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (arr[i][j] == 0) continue;
                arr[i][j] = arr[i-1][j] + arr[i][j-1];
                if (arr[i][j] >= 1000000007) {
                    arr[i][j] %= 1000000007;
                }
            }
        }

//        for (int[] a : arr) System.out.println(Arrays.toString(a));

        return arr[n-1][m-1];
    }
}