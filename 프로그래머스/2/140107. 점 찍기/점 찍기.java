class Solution {
    public long solution(int k, int d) {
        long answer = 0;

        // x 에 따라 y 의 최대값 구해서 계산
        int maxIdx = d;
        for (int i = 0; i <= d; i += k) {
            for (int j = maxIdx; j >= 0; j--) {
                if (check(i, j, d)) {
                    answer += j / k + 1;
                    maxIdx = j;
                    break;
                }
            }
        }

        return answer;
    }

    static boolean check(int x, int y, int d) {
        // 거리 계산
        double res = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        return res <= d;
    }
}