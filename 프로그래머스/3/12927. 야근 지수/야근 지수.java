import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long val = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        long total = 0;
        for (int w : works) {
            pq.add(-w);
            total += w;
        }

        if (total <= n) return 0;

        while (n > 0) {
            pq.add(pq.poll() + 1);
            n--;
        }

        while (!pq.isEmpty()) {
            int t = -pq.poll();
            val += t * t;
        }

        return val;
    }
}