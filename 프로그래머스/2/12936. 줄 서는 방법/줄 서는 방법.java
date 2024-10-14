class Solution {
    public int[] solution(int n, long k) {
        int[] answer = new int[n];
        boolean[] visited = new boolean[n+1];
        visited[0] = true;

        for (int i = 0; i < n; i++) {
            int idx = n-i-1; // 뒤에 몇명 남았는지

            // idx! 로 나눈 몫 +1 이 해당 순서의 사람
            long f = fact(idx);

            int human = (int)((k-1) / f);
            int head = 1; // 가장 앞에 있는 사람

            while (visited[head]) {
                head++;
            }
            
            while (human > 0) {
                head++;
                if (!visited[head]) {
                    human--;
                }
            }

            answer[i] = head+human;
            visited[head+human] = true;
            k = (k - 1) % f + 1;

        }

        return answer;
    }
    
    static long fact(int n) {
        // n! 리턴하는 메서드
        if (n <= 1) return 1;
        
        Long[] arr = new Long[n+1];
        arr[1] = 1l;
        
        for (int i = 2; i <= n; i++) {
            arr[i] = i * arr[i-1];
        }
        
        return arr[n];
    }
}