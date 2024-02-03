import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = sc.nextInt();
        int m = sc.nextInt();

        List<Integer>[] arr = new List[n+1]; // 인접리스트
        int[] cnt = new int[n+1]; // 진입차수

        for (int i = 0; i < n+1; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            arr[a].add(b);
            cnt[b]++;
        } // 인풋 받기 끝

        Queue<Integer> queue = new LinkedList<>();
        // 진입차수 0인 애들 큐에 넣어
        for (int i = 1; i < n+1; i++) {
            if (cnt[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int t = queue.poll();
            bw.write(t + " ");

            for (int i = 0; i < arr[t].size(); i++) {
                int v = arr[t].get(i);
                cnt[v]--;
                if (cnt[v] == 0) {
                    queue.add(v);
                }
            }
            for (int i = 0; i < arr[t].size(); i++) {
                arr[t].remove(i);
            }

        }

        bw.flush();
    }
}
