class Solution {
    public long solution(int n) {
        long answer = f(n);
        return answer % 1234567;
    }
    
    static long f(int n) {
        long[] memo = new long[n+1];
        
        if (n == 1) return 1;

        memo[1] = 1;
        memo[2] = 2;

        if (n <= 2) {
            return memo[n];
        }

        for (int i = 3; i <= n; i++) {
            memo[i] = (memo[i-2] + memo[i-1]) % 1234567;
        }

        return memo[n];

    }
}