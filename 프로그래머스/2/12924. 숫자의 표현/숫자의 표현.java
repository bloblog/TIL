class Solution {
    public int solution(int n) {
        int answer = 0;

        // 연속한 자연수의 개수 cnt
        int cnt = 1;
        while (true) {
            
            // len 1 -> x + 0 = 15
            // len 2 -> 2 * x + (0+1) = 15
            // len 3 -> 3 * x + (0+1+2) = 15
            // len 4 -> 4 * x + (0+1+2+3) = 15
            // len 5 -> 5 * x + (0+1+2+3+4) = 15

            // 0부터 cnt-1 까지의 합
            int s = sum(cnt-1);
            if ((n - s) % cnt == 0) {
                answer++;
            };

            if ((n - s) / cnt <= 1) break;
            cnt++;
        }

        return answer;
    }
    
    static int sum(int num) {
        return num * (num+1) / 2;
    }
}