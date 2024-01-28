import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int money = sc.nextInt();

        // 가치 같은 동전은 한 번만 담는다
        Set<Integer> coinSet = new HashSet<>();
        for (int i = 0; i < n; i++) {
            coinSet.add(sc.nextInt());
        } // 인풋 받기 끝

        // 동전 크기 순으로 정렬
        Integer[] coin = coinSet.toArray(new Integer[coinSet.size()]);
        Arrays.sort(coin);

        int[] dp = new int[money+1];

        int INF = 987654321;
        Arrays.fill(dp, INF);
        dp[0] = 0;

        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < coin.length; j++) {
                int c = coin[j];
                if (i >= c) {
                    dp[i] = Math.min(dp[i], dp[i-c] + 1);
                }
            }
        }

        int answer = -1;
        if (dp[money] != INF) {
            answer = dp[money];
        }
        System.out.println(answer);
    }
}
