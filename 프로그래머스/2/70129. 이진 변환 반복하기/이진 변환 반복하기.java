import java.util.Arrays;

class Solution {
    public int[] solution(String s) {
        int n = 0;
        cnt = 0;

        while (!s.equals("1")) {
            n++;

            // 0 제거
            s = deleteZero(s);

            // 이진변환
            s = Integer.toBinaryString(s.length());

        }

        return new int[] {n, cnt};
    }
    
    static int cnt;
    static String deleteZero(String s) {
        String newS = "";

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '0') newS += s.charAt(i);
            else cnt++;
        }

        return newS;
    }
}