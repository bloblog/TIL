import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        int N = enemy.length;

    // 무적권이 라운드 수 이상이면 모두 막을 수 있음
    if (k >= N) return N;

    // 병사 소모를 기록하는 최대 힙
    PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

    int round = 0;
    for (int t : enemy) {
        if (n >= t) {
            // 병사로 방어 가능한 경우
            n -= t;
            pq.add(t);
        } else if (k > 0) {
            // 무적권 사용
            if (!pq.isEmpty() && pq.peek() > t) {
                // 가장 병사 소모가 많았던 라운드에 무적권 사용
                n += pq.poll();
                n -= t;
                pq.add(t);
            }
            // 현재 라운드에 무적권 사용
            k--;
        } else {
            // 방어 불가
            break;
        }
        round++;
    }

    return round;
    }
}