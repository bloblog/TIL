import java.util.Arrays;

class Solution {
    public int solution(int[] citations) {
        int N = citations.length;

        Arrays.sort(citations);
        
        int cnt = 0;
        // 제일 큰 숫자부터 시작
        for (int i = citations[N-1]; i >= 0; i--) {
            cnt = 0;
            for (int j = N-1; j >= 0; j--) {
                if (i <= citations[j]) {
                    cnt++;
                }
                if (cnt >= i) return i;
            }
        }

        return 0;
    }
}