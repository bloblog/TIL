import java.util.*;

public class Main {
    static int M, answer;
    static int[] sel;
    static int[][] dist;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        answer = 987654321;

        int N = sc.nextInt();
        M = sc.nextInt();

        int[][] arr = new int[N][N];

        List<Home> home = new ArrayList<>(); // 집 정보
        List<Chicken> ck = new ArrayList<>(); // 치킨집 정보

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int val = sc.nextInt();
                arr[i][j] = val;
                if (val == 1) home.add(new Home(i, j));
                if (val == 2) ck.add(new Chicken(i, j));
            }
        } // 인풋 받기 끝

        dist = new int[home.size()][ck.size()]; // 각 집과 치킨집과의 거리

        for (int i = 0; i < home.size(); i++) {
            for (int j = 0; j < ck.size(); j++) {
                dist[i][j] = Math.abs(home.get(i).r - ck.get(j).r) + Math.abs(home.get(i).c - ck.get(j).c);
            }
        }

        sel = new int[M];
        comb(0,0, ck);

        System.out.println(answer);
    }

    static void comb(int idx, int sidx, List<Chicken> ck) {
        if (sidx == M) {
            // 선택된 치킨집만 남을 경우, 최소 거리 구하기
            int sum = 0;
            for (int[] d : dist) {
                int min = 987654321;
                for (int s : sel) {
                    if (d[s] < min) min = d[s];
                }
                sum += min;
            }
            if (sum < answer) answer = sum;
            return;
        }

        if (idx == ck.size()) return;
        
        sel[sidx] = idx;
        comb(idx+1, sidx+1, ck);
        comb(idx+1, sidx, ck);

    }
}

class Home {
    int r;
    int c;

    public Home(int r, int c) {
        this.r = r;
        this.c = c;
    }
}
class Chicken {
    int r;
    int c;

    public Chicken(int r, int c) {
        this.r = r;
        this.c = c;
    }
}