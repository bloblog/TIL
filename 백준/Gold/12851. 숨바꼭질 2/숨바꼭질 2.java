import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int N, K;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        K = sc.nextInt();

        count = new int[100001];
        cnt = 0;

        if (N != K) {
            bfs(N);
        } else {
            cnt = 1;
        }

        System.out.println(count[K]);
        System.out.println(cnt);

    }

    static int[] count;
    static int cnt;
    static void bfs(int st) {
        Queue<Integer> queue = new LinkedList<>();
        char[] opt = new char[] {'-', '+', '*'};

        queue.add(st);

        while (!queue.isEmpty()) {
            int t = queue.poll();

            if (count[K] != 0 && count[K] < count[t]) return;

            for (int i = 0; i < 3; i++) {
                int res = calc(t, opt[i]);

                if (res < 0 || res > 100000) continue;



                if (count[res] == 0 || count[res] >= count[t]+1) {
                    queue.add(res);
                    count[res] = count[t] + 1;

                    if (res == K) {
                        cnt++;
                    }
                }


            }
        }
    }

    static int calc(int n, char opt) {
        if (opt == '-') return n-1;
        else if (opt == '+') return n+1;
        else return 2*n;
    }
}
