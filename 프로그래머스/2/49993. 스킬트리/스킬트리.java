import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;

        Queue<Character> queue = new LinkedList<>();
        for (char c : skill.toCharArray()) {
            queue.add(c);
        } // 선행 스킬 순서

        for (int i = 0; i < skill_trees.length; i++) {
            boolean flag = true;
            Queue<Character> queue_copy = new LinkedList<>();
            Queue<Character> target = new LinkedList<>();

            for (char c : queue) {
                queue_copy.add(c);
            } // 복사

            String str = skill_trees[i];
            for (char c : str.toCharArray()) {
                if (queue.contains(c)) {
                    // 해당하는 스킬트리인 경우
                    target.add(c);
                }
            }

            // 선행 스킬 순서와 비교
            while (!target.isEmpty()) {
                if (target.poll() != queue_copy.poll()) {
                    flag = false;
                }
            }
            if (flag) {
                answer++;
            }
        }

        return answer;
    }
}