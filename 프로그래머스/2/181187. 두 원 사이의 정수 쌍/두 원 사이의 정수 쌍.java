class Solution {
    public long solution(int r1, int r2) {
        long answer = 0;
        
        // x 좌표가 1씩 증가할 떄 각 원의 y 좌표 구하기
        // 한 사분면만 구하기
        for (int x = 1; x < r2; x++) {
            int y2 = (int)Math.floor(getY(x, r2));
            int y1 = (int)Math.ceil(getY(x, r1));
            answer += y2 - y1 + 1;
        }

        return answer * 4 + 4;
    }

    static double getY(int x, int r) {
        // 반지름 제곱 - x 제곱 = y 제곱
        return Math.sqrt(Math.pow(r, 2) - Math.pow(x, 2));
    }
}