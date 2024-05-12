
import java.util.ArrayList;
import java.util.List;

class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;

        int st = 1; // 도달 못한 아파트 범위 시작
        int ed; // 도달 못한 아파트 범위 끝
        int cnt;
        for (int s : stations) {
            int[] t = new int[] {s-w, s+w};

            ed = t[0]-1 < 0 ? 0 : t[0]-1;
            cnt = ed - st + 1 < 0 ? 0 : ed - st + 1;

            // 딱 떨어지면 기지국 추가 안해도 됨
            if (cnt % (w*2+1) == 0) {
                answer += cnt / (w*2+1);
            } else {
                answer += cnt / (w*2+1) + 1;
            }
            st = (t[1]+1 < n+1) ? t[1]+1 : n+1;
        }

        // 마지막 아파트 도달시 처리
        if (st != n+1) {
            ed = n;
            cnt = ed - st + 1;

            if (cnt % (w*2+1) == 0) {
                answer += cnt / (w*2+1);
            } else {
                answer += cnt / (w*2+1) + 1;
            }
        }

        return answer;
    }
}