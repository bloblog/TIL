import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int L;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        } // 인풋 받기 끝

        int cnt = 0;
        for (int i = 0; i < N*2; i++) {
            int[] road = new int[N]; // 체크할 길

            if (i < N) {
                for (int j = 0; j < N; j++) road[j] = map[i][j];
            } else {
                for (int j = 0; j < N; j++) road[j] = map[j][i-N];
            }

            // 지나갈 수 있는지 체크
            if (check(road)) {
                cnt++;
            }
        }

        System.out.println(cnt);

    }

    static boolean check(int[] road) {
        boolean[] visited = new boolean[road.length]; // 경사로 유무
        int bf_h = road[0]; // 직전 칸의 높이
        int h = 0;
        for (int i = 0; i < road.length; i++) {
            h = road[i];
            if (h != bf_h) {
                if (Math.abs(h - bf_h) > 1) return false;
                if (bf_h < h) {
                    // 놓을 공간이 영역을 벗어나거나
                    if (i-L < 0) return false;

                    // 일정한 높이가 아니거나
                    // 이미 경사로가 있는 경우 false
                    if (visited[i-L] == true) return false;
                    int val = road[i-L];
                    for (int j = 0; j < L; j++) {
                        if (val != road[i-L+j]) return false;
                        if (visited[i-L+j]) return false;
                        visited[i-L+j] = true;
                    }
                } else {
                    // 놓을 공간이 영역을 벗어나거나
                    if (i+L-1 >= road.length) return false;

                    // 일정한 높이가 아니거나
                    // 이미 경사로가 있는 경우 false
                    if (visited[i] == true) return false;
                    int val = road[i];
                    for (int j = 0; j < L; j++) {
                        if (val != road[i+j]) return false;
                        if (visited[i+j]) return false;
                        visited[i+j] = true;
                    }
                }
            }
            bf_h = road[i];
        }
        return true;
    }
}
