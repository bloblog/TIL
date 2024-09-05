import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


class Solution {
    public int solution(int[] order) {
        int answer = 0;

        // 메인 컨테이너 queue
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= order.length; i++) {
            queue.add(i);
        }

        // 보조컨테이너 stack
        Stack<Integer> stack = new Stack<>();
        while (order[0] != queue.peek()) {
            stack.add(queue.poll());
        }

        // 상자 하나는 무조건 싣는다
        queue.poll();
        answer++;

        for (int i = 1; i < order.length; i++) {
            int t = order[i];

            while (!queue.isEmpty() && t != queue.peek()) {
                if (!stack.isEmpty() && stack.peek() == t) {
                    break;
                } else {
                    stack.add(queue.poll());
                }
            }

            if (!queue.isEmpty() && t == queue.peek()) {
                queue.poll();
                answer++;
                continue;
            }

            if (t == stack.peek()) {
                stack.pop();
                answer++;
                continue;
            }

            break;

        }

        return answer;
    }
}