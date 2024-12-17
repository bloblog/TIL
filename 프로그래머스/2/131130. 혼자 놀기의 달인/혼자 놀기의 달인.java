import java.util.*;

class Solution {
    public int solution(int[] cards) {
        List<Integer> len = new ArrayList<>(); // 그룹별 상자 수

        int n = cards.length;
        int done = 0; // 그룹에 들어간 상자 수 카운트
        boolean[] visited = new boolean[n+1];
        while (done < n) {
            for (int i = 1; i <= n; i++) {
                if (visited[i]) continue;

                // i 상자에서부터 그룹 생성
                List<Integer> group = new ArrayList<>();
                int box = i;
                while (!group.contains(box)) {
                    group.add(box);
                    visited[box] = true;
                    done++;
                    box = cards[box-1];
                }
                len.add(group.size());
            }
        }

        if (len.size() == 1) return 0; // 1번 그룹에 다 들어간 경우

        Integer[] arr = len.toArray(new Integer[0]);
        Arrays.sort(arr, Comparator.reverseOrder());

        return arr[0] * arr[1];
    }
}