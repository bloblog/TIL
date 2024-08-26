class Solution
{
    public int solution(int n, int a, int b)
    {
        int answer = 1;

        int small = (a < b) ? a : b;
        int big = (a > b) ? a : b;

        while (true) {
            // 둘이 붙는지 체크
            if (check(small, big)) {
                break;
            };

            // 값 갱신
            if (small % 2 == 1) {
                small = (small + 1) / 2;
            } else {
                small /= 2;
            }

            if (big % 2 == 1) {
                big = (big + 1) / 2;
            } else {
                big /= 2;
            }

            // 라운드 +1
            answer++;
        }


        return answer;
    }
    
    static boolean check(int a, int b) {
        // a, b 둘 차이가 1이면서
        // 작은 수가 홀수일 경우 둘이 붙는다
        if (b - a == 1 && a % 2 == 1) return true;
        return false;
    }
}