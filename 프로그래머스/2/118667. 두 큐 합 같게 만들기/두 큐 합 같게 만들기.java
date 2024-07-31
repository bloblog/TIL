import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        int len = queue1.length;

        // 배열을 큐로 변형
        Queue<Integer> q1 = toQueue(queue1);
        Queue<Integer> q2 = toQueue(queue2);

        // 큐의 초기 합 구하기
        long sum1 = getSum(q1);
        long sum2 = getSum(q2);
        long total = sum1 + sum2;

        // 총 합이 홀수인 경우 -1
        if (total % 2 == 1) return -1;

        // 원래 합이 같은 경우 0
        if (sum1 == sum2) return 0;

        // 합이 큰 큐에서 pop한 원소를 합이 작은 큐에 add
        while (sum1 != sum2) {
            if (sum1 > sum2) {
                if (q1.size() == 1) return -1;
                int val = q1.poll();
                q2.add(val);

                // 합에 반영
                sum1 -= val;
                sum2 += val;
            } else {
                if (q2.size() == 1) return -1;
                int val = q2.poll();
                q1.add(val);

                // 합에 반영
                sum1 += val;
                sum2 -= val;
            }
            answer++;
            
            // 절대 안되는 경우
            if (answer > 3 * len) return -1;
        }

        return answer;
    }
    
    static Queue<Integer> toQueue(int[] arr) {
        Queue<Integer> queue = new LinkedList<>();
        for (int a : arr) queue.add(a);
        return queue;
    }

    static long getSum(Queue<Integer> queue) {
        long sum = 0;
        for (int q : queue) sum += q;
        return sum;
    }
}