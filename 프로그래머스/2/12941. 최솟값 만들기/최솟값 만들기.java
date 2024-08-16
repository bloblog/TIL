import java.util.Arrays;

class Solution
{
    public int solution(int []A, int []B)
    {
        int answer = 0;

        // 작은 숫자 - 큰 숫자 를 곱해서 더해준다
        int n = A.length;

        Arrays.sort(A);
        Arrays.sort(B);

        for (int i = 0; i < n; i++) {
            answer += A[i] * B[n-i-1];
        }

        return answer;
    }
}