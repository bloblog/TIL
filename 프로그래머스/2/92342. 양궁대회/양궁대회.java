import java.util.*;

class Solution {
    public int[] solution(int n, int[] info) {
        // 라이언이 맞힐 화살 개수
        // 10 ~ 0점
        int[] score = new int[11];
        max = -1;

        // score 담을 pq
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                // 낮은 점수가 더 많아야 함
                for (int i = o1.length - 1; i >= 0; i--) {
                    if (o1[i] < o2[i]) return 1;
                    else if (o1[i] > o2[i]) return -1;
                }
                return 0;
            }
        });
        dfs(n, 10, info, score, pq);

        if (max == -1) {
            return new int[] {-1};
        }

        return pq.poll();
    }
    
    static int max;

    static void dfs(int n, int st, int[] info, int[] score, PriorityQueue<int[]> pq) {
        if (n == 0) {
            // 라이언이 score를 냈을 때 결과 판별
            int sumL = 0;
            int sumA = 0;
            for (int i = 10; i >= 0; i--) {
                // 둘 다 0이면 계산 x
                if (score[10-i] == 0 && info[10-i] == 0) continue;
                if (score[10-i] > info[10-i]) sumL += i;
                else sumA += i;
            }

            if (sumL > sumA && max <= sumL - sumA) {
                if (sumL - sumA != max) {
                    max = sumL - sumA;
                    pq.clear();
                }
                pq.add(score.clone());
            }
            return;
        }

        for (int i = st; i >= 0; i--) {
            // 남은 화살로 가능한지 체크
            int spend = info[10-i] + 1 > n ? n : info[10-i] + 1;
            score[10-i] += spend;
            dfs(n - spend, i, info, score, pq);
            score[10-i] -= spend;


        }
    }
}