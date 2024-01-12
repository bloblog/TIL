import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int[] solution(int[] prices) {
        int N = prices.length;
        int[] answer = new int[N];

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0 ; i < N ; i++) {
            queue.add(prices[i]);
        }

        int idx = 0;
        while (!queue.isEmpty()) {
            int t = queue.poll();
            for (int i = idx+1; i < N; i++) {
                if (prices[i] >= t) {
                    answer[idx]++;
                } else {
                    answer[idx]++;
                    break;
                }
            }
            idx++;
        }
        return answer;
    }
}