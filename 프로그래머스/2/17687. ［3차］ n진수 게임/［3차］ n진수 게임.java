import java.util.Stack;

class Solution {
    public String solution(int n, int t, int m, int p) {
        String answer = "";

        // 모든 사람이 말하는 숫자를 일단 다 붙이고
        // 해당 문자열이 t * m 만큼 되면 멈춘다
        String str = "";
        int num = 0;
        while (str.length() < t*m) {
            str += conv(n, num++);
        }

        // p의 순서에 맞게 붙여서 return
        for (int i = p; i < t*m+1; i += m) {
            answer += str.charAt(i-1);
        }
        
        return answer;
    }
    
    static String conv(int n, int num) {
        String res = "";

        // num 을 n진수로 변환
        Stack<Integer> stack = new Stack<>();
        while (n <= num) {
            stack.add(num % n);
            num /= n;
        }

        stack.add(num);

        while (!stack.isEmpty()) {
            int s = stack.pop();
            if (s >= 10) {
                if (s == 10) res += "A";
                if (s == 11) res += "B";
                if (s == 12) res += "C";
                if (s == 13) res += "D";
                if (s == 14) res += "E";
                if (s == 15) res += "F";
            } else {
                res += String.valueOf(s);
            }
        }

        return res;
    }
}