import java.util.*;

class Solution {
    public String[] solution(String[][] plans) {
        int n = plans.length;
        String[] answer = new String[n];

        // 전체 과제
        PriorityQueue<Subject> pq = new PriorityQueue<>((a, b) -> a.start - b.start);
        // 중간에 멈춘 과제
        Stack<Subject> stack = new Stack<>();

        for (String[] p : plans) {
            pq.add(new Subject(p[0], parseToMin(p[1]), Integer.parseInt(p[2])));
        }

        int done = 0;
        int now = 0;
        while (done < n) {
            Subject t = pq.poll();
            now = t.start;
            Subject next = pq.peek();

            // 남은 과제 없으면 현재 과제 -> 멈춘 과제들 순서대로 수행
            if (next == null) {
                answer[done++] = t.name;
                while (!stack.isEmpty()) {
                    answer[done++] = stack.pop().name;
                }
                break;
            }

            if (t.start + t.time > next.start) {
                // 다음 과제 전까지 못 끝내는 경우
                t.time -= next.start - t.start;
                stack.add(t);
                now = next.start;
            } else {
                answer[done++] = t.name;
                now += t.time;
            }

            // 시간 비는 경우 멈춘 과제 다시 수행
            while (!stack.isEmpty() && now < next.start) {
                t = stack.pop();
                // 못 끝내는 경우
                if (now + t.time > next.start) {
                    t.time -= next.start - now;
                    stack.add(t);
                    break;
                } else {
                    answer[done++] = t.name;
                    now += t.time;
                }
            }
        }

        return answer;
    }

    static int parseToMin(String time) {
        String[] arr = time.split(":");
        return Integer.parseInt(arr[0]) * 60 + Integer.parseInt(arr[1]);
    }
}

class Subject {
    String name;
    int start;
    int time; // 남은 시간

    public Subject(String name, int start, int time) {
        this.name = name;
        this.start = start;
        this.time = time;
    }

    @Override
    public String toString() {
        return "name=" + name +
                ", start=" + start +
                ", time=" + time;
    }
}