import java.util.Arrays;

class Solution {
    public int[] solution(int n, long left, long right) {
        int len = (int)(right - left) + 1;
        int[] answer = new int[len];

        // 2차원 배열에서의 시작 위치, 끝 위치 구하기
        int start_r = (int)(left / n) + 1;
        int start_c = (int)(left % n) + 1;
        int end_r = (int)(right / n) + 1;
        int end_c = (int)(right % n) + 1;

        int idx = 0;
        for (int i = start_r; i <= end_r; i++) {

            if (i == end_r && i == start_r) {
                for (int j = start_c; j <= end_c; j++) {
                    answer[idx++] = Math.max(i, j);
                }
            } else if (i == start_r) {
                for (int j = start_c; j <= n; j++) {
                    answer[idx++] = Math.max(i, j);
                }
            } else if (i == end_r) {
                for (int j = 1; j <= end_c; j++) {
                    answer[idx++] = Math.max(i, j);
                }
            } else {
                for (int j = 1; j <= n; j++) {
                    answer[idx++] = Math.max(i, j);
                }
            }
        }

        return answer;
    }
}