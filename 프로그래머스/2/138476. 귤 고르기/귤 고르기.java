import java.util.PriorityQueue;


class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;

        int[] cnt = new int[10000001];

        for (int t : tangerine) {
            cnt[t]++;
        }

        PriorityQueue<Tangerine> pq = new PriorityQueue<>();

        for (int i = 0 ; i < cnt.length; i++) {
            if (cnt[i] != 0) {
                pq.add(new Tangerine(i, cnt[i]));
            }
        }

        while (k > 0) {
            k -= pq.poll().cnt;
            answer++;
        }

        return answer;
    }
}

class Tangerine implements Comparable<Tangerine>{
    int idx;
    int cnt;

    public Tangerine(int idx, int cnt) {
        this.idx = idx;
        this.cnt = cnt;
    }

    @Override
    public int compareTo(Tangerine t) {
        return t.cnt - this.cnt;
    }
}