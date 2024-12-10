class Solution {
    public int solution(int n) {
        answer = 0;
        int[] pos = new int[n]; // 각 행의 퀸 위치
        nq(n, 0, pos);
        return answer;
    }
    
    static int answer;
    static void nq(int n, int row, int[] pos) {
        if (row == n) {
            answer++;
            return;
        }

        for (int i = 0; i < n; i++) {
            // 해당 위치에 놓을 수 있는지 체크
            if (!check(row, i, pos)) continue;

            pos[row] = i;
            nq(n, row+1, pos);
        }
    }

    static boolean check(int row, int now, int[] pos) {
        for (int i = 0; i < row; i++) {
            // 세로 체크
            if (pos[i] == now) return false;
            // 대각 체크
            if (Math.abs(pos[i] - now) == Math.abs(i - row)) return false;
        }

        return true;
    }
}