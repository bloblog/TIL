import java.util.*;

class Solution {
    public int solution(int[] picks, String[] minerals) {
        answer = Integer.MAX_VALUE;

        // 가지고 있는 곡괭이 개수
        int total = 0;
        for (int a : picks) total += a;

        // 곡괭이 보유 현황
        int[] myPick = new int[total];
        for (int i = 0; i < total; i++) {
            if (i < picks[0]) myPick[i] = 0; // 다이아몬드 곡괭이
            else if (i < picks[0] + picks[1]) myPick[i] = 1; // 철 곡괭이
            else myPick[i] = 2; // 돌 곡괭이
        }

        int m = minerals.length;
        // 사용할 곡괭이 개수
        // 광물 5개마다 곡괭이 달라짐
        int n = (m % 5 == 0 ? m / 5 : m / 5 + 1);

        boolean[] visited = new boolean[3];
        int[] p = new int[Math.min(n, total)];
        perm(0,  visited, p, picks, minerals);

        return answer;
    }

    static int answer;

    static int calc(int[] arr, String[] minerals) {
        int tired = 0;
        for (int i = 0; i < Math.min(arr.length * 5, minerals.length); i++) {
            // 쉬운 비교를 위해 정수로 변경
            int m = 0;
            if (minerals[i].equals("diamond")) m = 0;
            else if (minerals[i].equals("iron")) m = 1;
            else m = 2;

            if (arr[i / 5] <= m) tired += 1;
            else tired += Math.pow(5, arr[i / 5] - m);
        }
        return tired;
    }

    static void perm(int idx, boolean[] visited, int[] p, int[] picks, String[] minerals) {
        if (idx == p.length) {
            // 사용 순서에 따라 피로도 계산
            answer = Math.min(calc(p, minerals), answer);
            return;
        }

        for (int i = 0; i < 3; i++) {
            if (!visited[i] && picks[i] > 0) {
                p[idx] = i;
                picks[i]--;
                perm(idx+1, visited, p, picks, minerals);
                picks[i]++;
            }
        }
    }
}
