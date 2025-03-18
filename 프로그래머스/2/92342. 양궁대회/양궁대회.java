import java.util.*;

class Solution {
    public int[] solution(int n, int[] info) {
        // 어피치 10 ~ 0 과녁에 맞힌 화살 수 info
        answer = new int[11];

        shoot(0, 0, n, new int[11], info);

        if (max == 0) return new int[] {-1};
        return answer;
    }

    static int max = 0; // 점수 차
    static int[] answer;

    static boolean checkLowScore(int[] arr) {
        // 현재 answer 과 비교
        for (int i = 10; i >= 0; i--) {
            if (answer[i] == arr[i]) continue;
            if (answer[i] > arr[i]) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    static void shoot(int cnt, int st, int n, int[] shoot, int[] info) {
        if (cnt == n) {
            // 점수 차 계산
            int gap = calcScore(info, shoot);
            if (gap >= 0 && gap >= max) {
                if (gap > max) {
                    answer = Arrays.copyOf(shoot, 11);
                } else if (checkLowScore(shoot)) {
                    answer = Arrays.copyOf(shoot, 11);
                }
                max = gap;
            }
            return;
        }

        for (int i = st; i <= 10; i++) {
            if (info[i] + 1 <= n - cnt) {
                shoot[i] += info[i] + 1;
                shoot(cnt+info[i]+1, i, n, shoot, info);
                shoot[i] -= info[i] + 1;
            } else {
                shoot[i] += n - cnt;
                shoot(n, i+1, n, shoot, info);
                shoot[i] -= n - cnt;
            }
        }
    }

    static int calcScore(int[] apeach, int[] lion) {
        // 라이언 - 어피치 점수 리턴
        int a = 0;
        int l = 0;
        for (int i = 10; i >= 0; i--) {
            if (apeach[10-i] == 0 && lion[10-i] == 0) continue;
            if (apeach[10-i] >= lion[10-i]) {
                a += i;
            } else {
                l += i;
            }
        }
        return l - a;
    }
}