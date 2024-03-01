import java.util.PriorityQueue;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int food : scoville) {
            pq.add(food);
        }

        while(check(K, pq)) {
            // 음식 섞기
            int f1 = pq.poll();
            int f2 = pq.poll();
            pq.add(f1 + f2*2);

            answer++;

            // 불가능한 경우
            if (pq.size() == 1 && pq.peek() < K) {
                answer = -1;
                break;
            }
        }

        return answer;
    }
    static boolean check(int K, PriorityQueue<Integer> pq) {
        // K 보다 작은 수가 있는지 체크
        for (int i = 0; i < pq.size(); i++) {
            int val = pq.poll();
            if (val < K) {
                pq.add(val);
                return true;
            }
            pq.add(val);
        }
        return false;
    }
}