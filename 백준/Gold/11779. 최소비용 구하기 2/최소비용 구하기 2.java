import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Node {
    int v;
    int w;

    public Node(int v, int w) {
        this.v = v;
        this.w = w;
    }

    @Override
    public String toString() {
        return "Node{" +
                "v=" + v +
                ", w=" + w +
                '}';
    }
}

class Route {
    List<Integer> order;
    int result;

    public Route(List<Integer> order, int result) {
        this.order = order;
        this.result = result;
    }

    @Override
    public String toString() {
        return "Route{" +
                "order=" + order +
                ", result=" + result +
                '}';
    }
}

public class Main {
    static int n, m, st, ed;
    static int[] price;
    static boolean[] visited;
    static List<Node>[] arr;
    static int[] preCity;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tk;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        arr = new List[n+1];
        for (int i = 0; i < n+1; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            tk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(tk.nextToken());
            int b = Integer.parseInt(tk.nextToken());
            int val = Integer.parseInt(tk.nextToken());

            arr[a].add(new Node(b, val));
        }

        tk = new StringTokenizer(br.readLine());
        st = Integer.parseInt(tk.nextToken());
        ed = Integer.parseInt(tk.nextToken());
        // 인풋 받기 끝

        price = new int[n+1];
        Arrays.fill(price, Integer.MAX_VALUE);

        visited = new boolean[n+1];
        preCity = new int[n+1];

        dijk(st);
        System.out.println(price[ed]);

        // 경로 역추적
        int cnt = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(ed);
        while (preCity[ed] != 0) {
            cnt += 1;
            stack.push(preCity[ed]);
            ed = preCity[ed];
        }
        System.out.println(cnt + 1);
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }

    }

    static void dijk(int st) {
        price[st] = 0;
        preCity[st] = 0;

        for (int i = 0; i < n-1; i++) {
            int min = Integer.MAX_VALUE;
            int idx = -1;
            for (int j = 0; j < n+1; j++) {
                if (!visited[j] && price[j] < min) {
                    min = price[j];
                    idx = j;
                }
            }

            if (idx == -1) break;

            visited[idx] = true;

            for (Node n : arr[idx]) {
                if (!visited[n.v] && price[n.v] > price[idx] + n.w) {
                    preCity[n.v] = idx;
                    price[n.v] = price[idx] + n.w;
                }


            }

        }
    }

}
