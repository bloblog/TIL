import java.util.Arrays;
import java.util.Stack;

class Solution {
    public int[] solution(int[] numbers) {
        int n = numbers.length;
        int[] answer = new int[n];
        Arrays.fill(answer, -1);

        Stack<Integer> stack = new Stack<>();
        stack.add(0);

        for (int i = 1; i < n; i++) {
            // 비교 기준
            int t = numbers[i];

            while (!stack.isEmpty() && numbers[stack.peek()] < t) {
                answer[stack.pop()] = t;
            }
            stack.add(i);
        }

        return answer;
    }
}