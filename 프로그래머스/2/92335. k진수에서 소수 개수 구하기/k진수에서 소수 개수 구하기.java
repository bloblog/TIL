import java.util.Arrays;
import java.util.Stack;

class Solution {
    public int solution(int n, int k) {
        // n을 k진수로 변환
        Stack<Integer> stack = new Stack<>();
        while (n >= k) {
            stack.add(n % k);
            n /= k;
        }
        stack.add(n);

        String str = "";
        while (!stack.isEmpty()) {
            str += stack.pop();
        }

        // 0으로 split 해서 소수 체크
        return check(str.split("0"));
    }
    
    static int check(String[] arr) {
        int cnt = 0;

        for (String s : arr) {
            if (s.equals("")) continue;

            // s 소수 판별
            Long n = Long.valueOf(s);
            if (n == 1) continue;

            boolean flag = true;
            for (int i = 2; i <= Math.sqrt(n); i++) {
                if (n % i == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) cnt++;
        }
        return cnt;
    }
}