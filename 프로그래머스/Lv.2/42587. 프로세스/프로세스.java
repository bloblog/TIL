import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;

        Queue<Task> queue = new LinkedList<>();
        int N = priorities.length;
        for (int i = 0; i < N; i++) {
            queue.add(new Task(i, priorities[i]));
        }

        while (true) {
            Task max = new Task(-1, 0);
            for (int i = 0; i < queue.size(); i++) {
                // 현재 가장 우선순위 높고 순서 빠른 프로세스 찾자
                Task t = queue.poll();
                if (t.prior > max.prior) {
                    max = t;
                }
                queue.add(t);
            }

            // 해당 프로세스를 맨 앞으로 보내고 실행 대기 큐에서 뺀다
            // 방금 실행된 프로세스가 location 번호라면 끝
            Task tmp = queue.peek();
            while (tmp.num != max.num) {
                tmp = queue.poll();
                queue.add(tmp);
                tmp = queue.peek();
            }

            answer++;
            if (queue.poll().num == location) {
                break;
            }
        }
        return answer;
    }
}
class Task {
    int num; // 프로세스 번호
    int prior; // 우선순위

    public Task(int num, int prior) {
        this.num = num;
        this.prior = prior;
    }

    @Override
    public String toString() {
        return "Task{" +
                "num=" + num +
                ", prior=" + prior +
                '}';
    }
}