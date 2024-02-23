
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Schedule[] sch = new Schedule[N];

        for (int i = 0; i < N; i++) {
            String[] str = br.readLine().split(" ");
            int a = Integer.parseInt(str[0]);
            int b = Integer.parseInt(str[1]);
            sch[i] = new Schedule(a, b, b-a);
        } // 인풋 받기 끝

        Arrays.sort(sch, new Comparator<Schedule>() {
            @Override
            public int compare(Schedule o1, Schedule o2) {
                if (o1.ed == o2.ed) {
                    return o1.st - o2.st;
                } else {
                    return o1.ed-o2.ed;
                }
            }
        }); // 정렬 기준 1 : 시작 시간, 기준 2 : 걸리는 시간

        int cnt = 1;
        Schedule now = sch[0];

        for (int i = 1; i<N; i++) {
            Schedule t = sch[i];
            if (now.ed <= t.st) {
                cnt++;
                now = t;
            }
        }

        System.out.println(cnt);

    }
}

class Schedule {
    int st;
    int ed;
    int time;

    public Schedule(int st, int ed, int time) {
        this.st = st;
        this.ed = ed;
        this.time = time;
    }
}
