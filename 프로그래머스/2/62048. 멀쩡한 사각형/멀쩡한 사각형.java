class Solution {
    public long solution(int w, int h) {
        return (long)w * h - count(w, h);
    }
    
    static long count(int w, int h) {
        // 버려지는 정사각형 카운트
        long cnt = 0;
        
        // 정사각형인 경우
        if (w == h) {
            return w;
        }

        // 둘의 최대공약수 구하기
        int val = 0;
        for (int i = Math.min(w, h); i >= 1; i--) {
            if (w % i == 0 && h % i == 0) {
                val = i;
                break;
            }
        }

        int new_w = w / val;
        int new_h = h / val;
        // int max = Math.max(new_w, new_h);

        // new_w, new_h 일 때 버려지는 정사각형의 수
        // 둘 중 하나가 1인 경우
        if (new_w == 1 || new_h == 1) {
            return new_w * new_h * val;
        }
        
        // int temp = 0;
        // if (max == new_h) {
        //     temp = 2 * (new_h - 1);
        // } else {
        //     temp = 2 * (new_w - 1);
        // }

        cnt = (new_w + new_h - 1) * val;

        return cnt;
    }
}