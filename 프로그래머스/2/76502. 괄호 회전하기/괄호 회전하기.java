import java.util.Stack;

class Solution {
    public int solution(String s) {
        int cnt = 0;

        int len = s.length();
        char[] arr = s.toCharArray();

        // 문자열의 길이가 홀수이면 바로 0 리턴
        if (len % 2 == 1) return 0;

        for (int i = 0; i < len; i++) {
            if (i > 0) {
                // 한 칸씩 회전
                char temp = arr[0];
                for (int j = 0; j < len - 1; j++) {
                    arr[j] = arr[j+1];
                }
                arr[len-1] = temp;
            }
            // 올바른 괄호인지 체크 및 cnt 반영
            if (check(arr)) cnt++;
        }

        return cnt;
    }
    
    static boolean check(char[] arr) {
        Stack<Character> stack = new Stack<>();

        // 하나씩 집어넣는다
        for (char c : arr) {
            // 아무것도 없을 때
            if (stack.size() == 0) {
                // 닫힌 괄호는 못 들어간다
                if (isClose(c)) return false;
            } else {
                // 닫힌 괄호인 경우, 맨 위의 원소와 짝 안맞으면 x
                if (isClose(c) && !isPair(stack.peek(), c)) return false;
                if (isClose(c) && isPair(stack.peek(), c)) {
                    // 짝 맞으면 스택에 있던 괄호 나가
                    stack.pop();
                    continue;
                }
            }
            stack.add(c);
        }

        // 다 돌았는데 스택에 남아있으면 x
        if (stack.size() > 0) return false;

        return true;
    }
    
    static boolean isClose(char c) {
        if (c == '(' || c == '{' || c == '[') return false;
        return true;
    }

    static boolean isPair(char open, char close) {
        if (open == '(' && close == ')') return true;
        if (open == '{' && close == '}') return true;
        if (open == '[' && close == ']') return true;
        return false;
    }
}