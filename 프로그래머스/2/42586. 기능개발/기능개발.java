import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> endDate = new ArrayList<>();

        int N = progresses.length;
        // 작업 담기
        Queue<Task> tasks = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            tasks.add(new Task(progresses[i], speeds[i]));
        }

        // 작업 시작
        while (!tasks.isEmpty()) {
            

            // 하루만큼 작업 수행
            for (int i = 0; i < tasks.size(); i++) {
                Task t = tasks.poll();
                t.progress += t.speed;
                tasks.add(t);
            }
            
            // 완료된 작업 배포
            int done = 0;
            int size = tasks.size();
            for (int i = 0; i < size; i++) {
                if (tasks.peek().progress >= 100) {
                    tasks.poll();
                    done++;
                } else {
                    break;
                }
            }
            if (done != 0) {
                endDate.add(done);
            }
        }

        // 자료형 변경해서 리턴
        int[] answer = new int[endDate.size()];
        for (int i = 0; i < endDate.size(); i++) {
            answer[i] = endDate.get(i);
        }

        return answer;
    }
}

class Task {
    int progress;
    int speed;

    public Task(int progress, int speed) {
        this.progress = progress;
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "Task{" +
                "progress=" + progress +
                ", speed=" + speed +
                '}';
    }
}