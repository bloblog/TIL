import java.util.Arrays;

class Solution {
    public String solution(int n) {
        String answer = "";

        int pos = 0; // 124숫자 자릿수
        int last = 0; // 해당 자릿수의 마지막 10진법 숫자
        while (n > last) {
            last += Math.pow(3, ++pos);
        }

        // 마지막 숫자에서 내려오면서 해당 숫자 찾기
        // 자릿수 별 내려가야 할 정도
        int[] change = new int[pos];

        int gap = last - n;
        int idx = pos-1;

        while (gap > 0) {
            change[idx--] = gap % 3;
            gap /= 3;
        }

        for (int i = 0; i < pos; i++) {
            // 변환 후 문자열로 합침
            if (change[i] == 1) {
                answer += "2";
            } else if (change[i] == 2) {
                answer += "1";
            } else {
                answer += "4";
            }
        }

        return answer;
    }
}