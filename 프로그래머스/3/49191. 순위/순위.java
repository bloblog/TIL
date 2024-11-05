import java.util.HashSet;
import java.util.Set;

class Solution {
    public int solution(int n, int[][] results) {
        int answer = 0;

        Set<Integer>[] win = new HashSet[n+1];
        Set<Integer>[] lose = new HashSet[n+1];
        for (int i = 0; i <= n; i++) {
            win[i] = new HashSet<>();
            lose[i] = new HashSet<>();
        }

        // 경기결과 set 에 넣기
        for (int i = 0; i < results.length; i++) {
            int a = results[i][0];
            int b = results[i][1];
            win[a].add(b);
            lose[b].add(a);
        }

        // 경기결과 바탕으로 경기결과 업데이트
        boolean isUpdated = true;
        while (isUpdated) {
            isUpdated = update(win);
        }

        isUpdated = true;
        while (isUpdated) {
            isUpdated = update(lose);
        }

        // win + lose 원소개수 합이 n-1 이면 확정
        for (int i = 1; i <= n; i++) {
            if (win[i].size() + lose[i].size() == n-1) {
                answer++;
            }
        }

        return answer;
    }
    
    static boolean update(Set<Integer>[] res) {
        boolean isUpdated = false;
        for (int i = 1; i < res.length; i++) {
            // copy
            Set<Integer> temp = new HashSet<>();
            int cnt = res[i].size();
            for (Integer j : res[i]) {
                temp.add(j);
            }

            for (Integer t : temp) {
                for (Integer r : res[t]) {
                    res[i].add(r);
                }
            }

            if (res[i].size() != cnt) {
                isUpdated = true;
            }
        }

        return isUpdated;
    }
}