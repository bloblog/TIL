import java.util.*;

public class Main {
    static int V, E;

    static int[] parent;
    static int[] rank;

    static PriorityQueue<Node> arr;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        V = sc.nextInt();
        E = sc.nextInt();

        parent = new int[V + 1];
        for (int i = 0; i < V+1; i++) {
            parent[i] = i;
        }

        rank = new int[V + 1];

        arr = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.val - o2.val;
            }
        });

        for (int i = 0; i < E; i++) {
            arr.add(new Node(sc.nextInt(), sc.nextInt(), sc.nextInt()));
        } // 인풋 받기 끝

        int sum = 0;

        while (!arr.isEmpty()) {
            Node n = arr.poll();

            if (find(n.st) == find(n.ed)) {
                continue;
            }

            union(n.st, n.ed);
            sum += n.val;
        }

        System.out.println(sum);

    }

    // 유니온 - 파인드 메서드
    static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y) {
            return;
        }

        if (rank[x] < rank[y]) {
            parent[x] = y;
        } else {
            parent[y] = x;

            if (rank[x] == rank[y]) {
                rank[x]++;
            }
        }
    }
}

class Node {
    int st;
    int ed;
    int val; // 가중치

    public Node(int st, int ed, int val) {
        this.st = st;
        this.ed = ed;
        this.val = val;
    }

    @Override
    public String toString() {
        return "Node{" +
                "st=" + st +
                ", ed=" + ed +
                ", val=" + val +
                '}';
    }
}
