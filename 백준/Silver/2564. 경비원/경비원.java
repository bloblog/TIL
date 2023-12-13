import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int a = sc.nextInt(); // 가로길이
        int b = sc.nextInt(); // 세로길이
        int n = sc.nextInt(); // 상점의 개수

        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = sc.nextInt();
            arr[i][1] = sc.nextInt();
        } // 상점 위치 받기

        int[] dg = new int[2];
        dg[0] = sc.nextInt();
        dg[1] = sc.nextInt();
        // 동근 위치 받기

        int[] len = new int[n]; // 최단거리 배열
        for (int i = 0; i < n; i++) {
            if (dg[0] == arr[i][0]) {
                // 둘 방향 같은 경우
                len[i] = Math.abs(dg[1] - arr[i][1]);
            } else if ((dg[0] == 2 && arr[i][0] == 1) || (dg[0] == 1 && arr[i][0] == 2)) {
                // 남쪽, 북쪽인 경우
                int dist = b + dg[1] + arr[i][1];
                if (dist > a+b) {
                    len[i] = 2*(a+b) - dist;
                } else {
                    len[i] = dist;
                }
            } else if ((dg[0] == 3 && arr[i][0] == 4) || (dg[0] == 4 && arr[i][0] == 3)) {
                // 서쪽, 동쪽인 경우
                int dist = a + dg[1] + arr[i][1];
                if (dist > a+b) {
                    len[i] = 2*(a+b) - dist;
                } else {
                    len[i] = dist;
                }
            } else if ((dg[0] == 3 && arr[i][0] == 2) || (dg[0] == 2 && arr[i][0] == 3)) {
                // 서쪽, 남쪽인 경우
                int dist = 0;
                if (dg[0] == 3) {
                    dist = b - dg[1] + arr[i][1];
                } else {
                    dist = b - arr[i][1] + dg[1];
                }
                len[i] = dist;
            } else if ((dg[0] == 4 && arr[i][0] == 2) || (dg[0] == 2 && arr[i][0] == 4)) {
                // 동쪽, 남쪽인 경우
                len[i] = a + b - dg[1] - arr[i][1];
            } else if ((dg[0] == 1 && arr[i][0] == 3) || (dg[0] == 3 && arr[i][0] == 1)) {
                // 북쪽, 서쪽인 경우
                len[i] = dg[1] + arr[i][1];
            } else if ((dg[0] == 4 && arr[i][0] == 1) || (dg[0] == 1 && arr[i][0] == 4)) {
                // 동쪽, 북쪽인 경우
                int dist = 0;
                if (dg[0] == 1) {
                    dist = a - dg[1] + arr[i][1];
                } else {
                    dist = a - arr[i][1] + dg[1];
                }
                len[i] = dist;
            }
        }

        // 최단거리 합 구하기
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += len[i];
        }

        System.out.println(sum);
    }
}
