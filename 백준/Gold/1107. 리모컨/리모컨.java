import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        List<String> broken = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            broken.add(String.valueOf(sc.nextInt()));
        }

        // 100에서 바로 방향키로만 가는 경우
        int min = Math.abs(n - 100);

        // 숫자 + 방향키로 가는 경우
        // 이 때 max 보다 많이 누르는 경우는 포함 x
        int start = n - min < 0? 0 : n-min;
        int end = n + min;
        for (int i = start; i < end; i++) {
            String s = String.valueOf(i);

            // 못 누르는 경우면 x
            boolean flag = false;
            for (int j = 0; j < s.length(); j++) {
                if (broken.contains(s.substring(j, j+1))) {
                    flag = true;
                };
            }

            if (flag) continue;

            // 숫자 i를 누르기 위한 버튼 누르는 개수
            int val = s.length() + Math.abs(n-i);
            if (val < min) {
                min = val;
//                System.out.println(min + " " + s);
            }
            if (min == 0) break;
        }

        System.out.println(min);
    }
}
