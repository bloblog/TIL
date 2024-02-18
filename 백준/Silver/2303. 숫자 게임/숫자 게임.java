import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int[][] cards;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        cards = new int[N][5];
        for (int i = 0; i< N; i++) {
            for (int j = 0; j < 5; j++) {
                cards[i][j] = sc.nextInt();
            }
        } // 인풋 받기 끝

        int max = 0;
        int winner = 0;
        for (int i = 0; i < N; i++) {
            res = new int[3]; // 뽑힌 카드들
            val = 0; // i 번째 사람의 카드 합 최대값
            comb(i, 0, 0);

            if (max <= val) {
                max = val;
                winner = i;
            };
        }

        System.out.println(winner+1);
    }

    static int[] res;
    static int val;
    static void comb(int person, int sidx, int idx) {
        if (sidx == 3) {
            // 뽑힌 숫자들 합 구해서 가장 큰 수 뽑기
            int sum = res[0] + res[1] + res[2];
            if (val < sum%10) {
                val = sum%10;
            }
            return;
        }

        if (idx == 5) {
            return;
        }

        res[sidx] = cards[person][idx];
        comb(person, sidx+1, idx+1);
        comb(person, sidx, idx+1);

    }
}
