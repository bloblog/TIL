import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        int answer = 1;

        Arrays.sort(targets, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o2[1] - o1[1];
                }
                return o1[0] - o2[0];
            }
        });

        int[] between = new int[] {targets[0][0], targets[0][1]};
        for (int i = 1; i < targets.length; i++) {
            int st = targets[i][0];
            int ed = targets[i][1];

            if (st < between[1]) {
                between[0] = st;
                if (ed < between[1]) {
                    between[1] = ed;
                }
            } else if (st == between[0] && ed < between[1]) {
                between[1] = ed;
            } else {
                answer++;
                between[0] = st;
                between[1] = ed;
            }
        }

        return answer;
    }
}