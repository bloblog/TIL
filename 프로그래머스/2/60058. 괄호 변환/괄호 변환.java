import java.util.Stack;

class Solution {
    public String solution(String p) {
        // 이미 올바른 경우
        if (isRight(p)) return p;

        // 올바르지 않은 경우 처리
        String[] div = divide(p);
        String u = div[0];
        String v = div[1];

        if (isRight(u)) {
            return u + solution(v);
        } else {
            // u의 맨 앞 뒤 문자 제거 후 뒤집기
            return "(" + solution(v) + ")" + turnString(u);
        }
    }
    
    static String turnString(String str) {
        if (str.length() <= 2) return "";

        // 맨 앞 뒤 문자 제거
        String cutStr = str.substring(1, str.length()-1);

        // 뒤집기
        String res = "";
        for (int i = 0; i < cutStr.length(); i++) {
            if (cutStr.charAt(i) == '(') {
                res += ')';
            } else {
                res += '(';
            }
        }
        return res;
    }

    static String[] divide(String p) {
        // p를 u,v 로 분리
        int cnt = 0;
        String u = "";
        String v = "";
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '(') cnt++;
            if (i + 1 - cnt == cnt) {
                u = p.substring(0, i+1);
                v = p.substring(i+1, p.length());
                break;
            }
        }
        return new String[] {u, v};
    }

    static boolean isRight(String str) {
        // 올바른 괄호 문자열인지 판별

        // 빈 문자열 주어지면 true 처리
        if (str.length() == 0) return true;

        // 닫는 괄호가 맨 처음 나오면 false
        if (str.charAt(0) == ')') return false;

        Stack<Character> stack = new Stack<>();
        stack.push(str.charAt(0));

        for (int i = 1; i < str.length(); i++) {
            Character t = str.charAt(i);
            if (t == '(') {
                stack.push(str.charAt(i));
            } else {
                if (stack.size() == 0) return false;

                if (stack.pop() != '(') {
                    return false;
                }
            }
        }

        if (stack.size() > 0) return false;

        return true;
    }
}