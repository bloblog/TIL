import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        int n = jobs.length; // 작업 개수
        int end = 0; // 완수시간
        int count = 0; // 끝난 작업 개수

        // 시작 시간 순으로 정렬
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);

        PriorityQueue<Job> job = new PriorityQueue<>();

        int i = 0;
        while (count < n) {
            // 현재 시간보다 시작 시간 빠른 경우 큐에 추가
            while (i < n && jobs[i][0] <= end) {
                job.add(new Job(jobs[i][0], jobs[i][1]));
                i++;
            }

            if (job.isEmpty()) {
                // 작업 없는 경우 먼저 들어온 작업 처리
                end = jobs[i][0];
            } else {
                Job now = job.poll();
                // 해당 작업 수행
                answer += end - now.st + now.time;
                end += now.time;
                count++;
            }
        }
        return answer / n;
    }
}

class Job implements Comparable<Job>{
    int st;
    int time; // 소요시간

    public Job() {};

    public Job(int st, int time) {
        this.st = st;
        this.time = time;
    }

    @Override
    public int compareTo(Job o) {
        return this.time - o.time;
    }

    @Override
    public String toString() {
        return "st=" + st +
                ", time=" + time;
    }
}