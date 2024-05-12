import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int n = A.length;
        int answer = 0;

        // A, B를 작은 순으로 정렬
        Arrays.sort(A);
        Arrays.sort(B);

        // B 돌면서 이길 수 있는 A의 카드 찾는다
        int idx = 0; // A 배열의 인덱스
        for (int i = 0; i < n; i++) {
            int b = B[i];
            if (idx < n && A[idx] < b) {
                answer++;
                idx++;
            }
            
        }

        return answer;
    }
}