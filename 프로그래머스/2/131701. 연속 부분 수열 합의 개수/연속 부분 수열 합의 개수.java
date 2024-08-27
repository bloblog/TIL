import java.util.HashSet;
import java.util.Set;

class Solution {
    public int solution(int[] elements) {
        int len = elements.length;
        int n = 1;
        Set<Integer> set = new HashSet<>();

        // 길이가 n 인 부분 수열
        while (n <= len) {
//            System.out.println("부분 수열 길이 = " + n);
            // 연속하는 부분 수열 시작 위치
            for (int i = 0; i < len; i++) {
                int sum = 0;
                for (int j = 0; j < n; j++) {
                    // 실제 원소 인덱스는 idx % len
                    int idx = i + j;
//                    System.out.println("선택원소 = " + elements[idx % len]);
                    sum += elements[idx % len];
                }
                set.add(sum);
            }
            n++;
        }

        return set.size();
    }
}