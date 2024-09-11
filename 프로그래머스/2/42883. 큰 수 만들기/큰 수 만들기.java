
class Solution {
    public String solution(String number, int k) {
        String answer = "";

        // answer의 자릿수
        int len = number.length() - k;

        // 각 자리별로 가능한 숫자 범위 안에서 가장 큰 수 선택
        int st = 0;
        int ed = number.length() - len;
        int idx = 0; // 자릿수 인덱스

        while (idx++ < len) {
            int max = -1;
            int max_idx = -1;
            for (int i = st; i <= ed; i++) {
                int val = number.charAt(i) - 48;
                if (max < val) {
                    max = val;
                    max_idx = i;
                    if (val == 9) break;
                }
            }

            answer += max;

            // 범위 갱신
            st = max_idx+1;
            ed++;

        }
        return answer;
    }
}