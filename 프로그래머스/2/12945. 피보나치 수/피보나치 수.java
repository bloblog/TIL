class Solution {
    public int solution(int n) {
        return fibo(n);

    }
    
    static int fibo(int n) {
        int[] memo = new int[n+1];
        memo[1] = 1;

        for (int i = 2; i <= n; i++) {
            memo[i] = (memo[i-2] % 1234567 + memo[i-1] % 1234567) % 1234567;
        }
        return memo[n];
    }
}