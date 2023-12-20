import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int V,E;
    static int[] dist; // 최단 경로 담을 배열
    static List<Node>[] arr; // 인접리스트 담을 배열
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        // 초기화
        arr = new ArrayList[V+1];
        for (int i = 0; i < V+1; i++) {
            arr[i] = new ArrayList<>();
        }

        int K = Integer.parseInt(br.readLine());

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            arr[u].add(new Node(v, w));
        }

//        System.out.println(Arrays.toString(arr));

        // dist 배열 초기화
        dist = new int[V+1];
        Arrays.fill(dist, INF);

        dijkstra(K);

        // 결과 출력
        for (int i = 1; i < V+1; i++) {
            if (dist[i] == INF) {
                System.out.println("INF");
            } else {
                System.out.println(dist[i]);
            }
        }

    }

    static void dijkstra(int st) {
        boolean[] visited = new boolean[V+1];

        dist[st] = 0;

        for (int i = 0; i < V-1; i++) {
            int min = INF;
            int idx = -1;

            for (int j = 1; j < V+1; j++) {
                if (!visited[j] && dist[j] < min) {
                    min = dist[j];
                    idx = j;
                }
            }

            // 다 INF거나 다 방문했다면 나가
            if (idx == -1) break;

            // 아니면 선택해
            visited[idx] = true;

            // 해당 노드와 연결된 노드들을 돌아
            for (Node n : arr[idx]) {
                if (!visited[n.v] && dist[n.v] > dist[idx] + n.w) {
                    // 갱신
                    dist[n.v] = dist[idx] + n.w;
                }
            }
        }

    }

}

class Node {
    int v, w;

    public Node(int v, int w) {
        this.v = v;
        this.w = w;
    }
}
