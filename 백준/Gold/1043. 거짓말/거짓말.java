import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 사람의 수
        int M = sc.nextInt(); // 파티의 수

        int n = sc.nextInt(); // 진실 아는 사람의 수
        if (n == 0) {
            System.out.println(M);
        } else {
            // 진실을 아는 사람
            Queue<Integer> queue = new LinkedList<>();
            // 진실 아는 사람 저장
            List<Integer> arr = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                int val = sc.nextInt();
                arr.add(val);
                queue.add(val);
            }

            List<Integer>[] graph = new List[N+1]; // 연결 리스트
            for (int i = 0; i < N+1; i++) {
                graph[i] = new ArrayList<>();
            } // 초기화

            List<Integer>[] party = new List[M]; // 파티 리스트

            // 같은 파티에 참여 = 양방향 연결
            for (int i = 0; i < M; i++) {
                party[i] = new ArrayList<>();

                int p = sc.nextInt(); // 파티 참여 인원
                for (int j = 0; j < p; j++) {
                    party[i].add(sc.nextInt());
                } // 파티 참여 인원 배열에 저장

                for (int a : party[i]) {
                    for (int b : party[i]) {
                        if (a != b) {
                            if (!graph[a].contains(b)) {
                                graph[a].add(b);
                            }
                            if (!graph[b].contains(a)) {
                                graph[b].add(a);
                            }
                        }
                    }
                }
            } // 인풋 받기 끝


            while (!queue.isEmpty()) {
                int t = queue.poll();
                for (int g : graph[t]) {
                    if (!arr.contains(g)) {
                        queue.add(g);
                        arr.add(g);
                    }
                }
            }

            // 파티 돌면서 과장 가능한 파티 카운트
            int cnt = 0;
            for (int i = 0; i < M; i++) {
                boolean flag = false;
                for (int p : party[i]) {
                    if (arr.contains(p)) {
                        flag = true;
                        break;
                    };
                }
                if (!flag) {
                    cnt++;
                }
            }

            System.out.println(cnt);
        }
    }
}
