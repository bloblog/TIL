import java.util.*;

class Solution {
    public int solution(int[] stones, int k) {
        int answer = 0;

        // k개씩 끊었을 때 최대값 중 최소값 구하기
        Deque<Integer> dq = new LinkedList<>();
        // 초기 세팅
        for (int i = 0; i < k; i++) {
            while (!dq.isEmpty() && stones[dq.peekLast()] <= stones[i]) {
                dq.pollLast();
            }
            dq.offerLast(i);
        }

        answer = stones[dq.peekFirst()];

        for (int i = k; i < stones.length; i++) {
            // 현재 최대값이 윈도우 범위 벗어나는지 체크
            if (dq.peekFirst() <= i-k) {
                dq.pollFirst();
            }

            // 다시 최대값을 가지는 인덱스만 남겨
            while (!dq.isEmpty() && stones[dq.peekLast()] <= stones[i]) {
                dq.pollLast();
            }
            dq.offerLast(i);

            if (stones[dq.peekFirst()] < answer) {
                answer = stones[dq.peekFirst()];
            }
        }

        return answer;
    }
}