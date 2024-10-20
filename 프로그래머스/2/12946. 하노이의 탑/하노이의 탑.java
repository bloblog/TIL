import java.util.*;

class Solution {
    public int[][] solution(int n) {
        List<int[]> route = new ArrayList<>(); // 이동 경로

        move(n, 1, 2, 3, route);

        return route.toArray(new int[][] {});
    }
    
    static void move(int n, int st, int temp, int ed, List<int[]> route) {
        // st -> ed 로 이동
        // temp 는 중간 저장소
        // n = 원판 개수
        if (n == 1) {
            route.add(new int[] {st, ed});
            return;
        }

        move(n-1, st, ed, temp, route);

        // 가장 큰 원판 이동
        route.add(new int[] {st, ed});

        move(n-1, temp, st, ed, route);
    }
}