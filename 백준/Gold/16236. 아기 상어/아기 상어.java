import java.util.*;

public class Main {
    static int N;
    static int[][] arr;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        Shark shark = new Shark(); // 아기상어 정보
        int cnt = 0; // 크기가 2일 때 먹을 수 있는 물고기의 수

        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j< N; j++) {
                int val = sc.nextInt();
                arr[i][j] = val;
                if (val == 9) {
                    shark = new Shark(2, 0, i,j);
                } else if (val != 0 && val < 2) {
                    cnt++;
                }
            }
        }

        time = 0;
        if (cnt != 0) {
            // 가장 가까운 먹을 수 있는 물고기 계속 찾는 메서드
            searchFish(shark);
        }

        System.out.println(time);

    }

    static int time;
    static int[] dr = new int[] {-1, 0, 0, 1};
    static int[] dc = new int[] {0, -1, 1, 0};
    static void searchFish(Shark s) {

        List<int[]> fishList = new ArrayList<>();
        int[][] dist = new int[N][N];
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];

        visited[s.r][s.c] = true;
        queue.add(new int[] {s.r, s.c});
        arr[s.r][s.c] = 0;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            for (int d = 0; d<4; d++) {
                int nr = now[0] + dr[d];
                int nc = now[1] + dc[d];

                if (nr < 0 || nr >= N || nc <0 || nc >= N) continue;

                if (!visited[nr][nc] && arr[nr][nc] <= s.size) {
                    visited[nr][nc] = true;
                    queue.add(new int[] {nr, nc});
                    dist[nr][nc] = dist[now[0]][now[1]] + 1;

                    // 만약 먹을 수 있는 물고기라면
                    if (arr[nr][nc] != 0 && arr[nr][nc] < s.size) {
                        fishList.add(new int[] {dist[nr][nc], nr, nc});
                    }
                }
            }
        }
        // 행열 순서대로 물고기 먹고 다음 물고기를 탐색
        if (!fishList.isEmpty()) {
            Collections.sort(fishList, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    if (o1[0] == o2[0]) {
                        if (o1[1] == o2[1]) {
                            return o1[2] - o2[2];
                        }
                        return o1[1] - o2[1];
                    }
                    return o1[0] - o2[0];
                }
            });

            int[] target = fishList.get(0);
            time += target[0];
            arr[target[1]][target[2]] = 0;

            Shark shark;
            // 크기 증가하는 경우, 상어 상태 바꿔줌
            if (s.fish+1 == s.size) {
                shark = new Shark(s.size+1, 0, target[1], target[2]);
            } else {
                shark = new Shark(s.size, s.fish+1, target[1], target[2]);
            }
            searchFish(shark);
        }
    }
}

class Shark {
    int size;
    int fish; // 먹은 물고기의 수
    int r, c; // 처음 위치

    public Shark() {}

    public Shark(int size, int fish, int r, int c) {
        this.size = size;
        this.fish = fish;
        this.r = r;
        this.c = c;
    }
}
