import java.util.Arrays;

class Solution {
    public int[] solution(int n, int s) {
        int[] answer = new int[n];

        if (s / n == 0) return new int[] {-1};

        int val = s / n;
        for (int i = 0; i < n; i++) {
            answer[i] = val;
        }

        int iter = s % n;
        if (iter > 0) {
            for (int i = n-1; i > n-1-iter; i--) {
                answer[i]++;
            }
        }

        return answer;
    }
}