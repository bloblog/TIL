import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;


public class Main {
    static List<Bus>[] arr;
    static int[] dist;
    static final int INF = Integer.MAX_VALUE;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        arr = new List[N+1];
        for (int i = 0; i < N+1; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            arr[start].add(new Bus(end, cost));
        }

        st = new StringTokenizer(br.readLine());
        int st_city = Integer.parseInt(st.nextToken());
        int ed_city = Integer.parseInt(st.nextToken());
        // 인풋 받기 끝

        dijkstra(st_city);

        System.out.println(dist[ed_city]);

    }

    static void dijkstra(int st) {
        dist = new int[N+1];
        Arrays.fill(dist, INF);

        boolean[] visited = new boolean[N+1];

        dist[st] = 0;

        // 본인 빼고 돈다 = N-1 번 돈다
        for (int i = 0; i < N-1; i++) {
            int min = INF;
            int idx = -1;
            for (int j = 0; j < N+1; j++) {
                if (!visited[j] && dist[j] < min) {
                    min = dist[j];
                    idx = j;
                }
            }
            if (idx == -1) break;

            // 연결된 노드 있는 경우 선택해
            visited[idx] = true;
            for (Bus b : arr[idx]) {
                if (!visited[b.ed] && dist[b.ed] > dist[idx] + b.w) {
                    dist[b.ed] = dist[idx] + b.w;
                }
            }
        }
    }
}

class Bus {
    int ed; // 도착지
    int w; // 가중치 = 버스 비용

    public Bus(int ed, int w) {
        this.ed = ed;
        this.w = w;
    }
}