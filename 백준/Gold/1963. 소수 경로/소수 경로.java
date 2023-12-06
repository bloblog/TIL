import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int st, ed;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            st = sc.nextInt();
            ed = sc.nextInt();
            cnt = 0;

            if (st == ed) {
                System.out.println(cnt);
            } else {
                // 최소 횟수 = bfs
                bfs(st);
                if (cnt == 0) {
                    System.out.println("Impossible");
                } else {
                    System.out.println(cnt);
                }

            }
        }
    }

    static int cnt; // 변환 횟수
    static int[] count;
    static void bfs(int st) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[10000];
        count = new int[10000];

        visited[st] = true;
        queue.add(st);

        while (!queue.isEmpty()) {
            int t = queue.poll();
            char[] list = String.valueOf(t).toCharArray();
            // n = 바꿔 넣을 숫자
            for (int n = 0; n < 10; n++) {
                // i = 바꿀 자릿수
                for (int i = 0; i < 4; i++) {
                    // 원래 숫자 저장
                    char ori = list[i];

                    if (n != list[i]) {
                        // 한 자리 변경
                        list[i] = Character.forDigit(n ,10);

                        // 소수인지 체크하고, 맞으면 큐에 넣고 count +1 해준다
                        // 1000 이상인지도 확인해야 함
                        int tmp = toNum(list);
                        if (!visited[tmp] && isPrime(tmp) && tmp >= 1000) {
                            visited[tmp] = true;
                            queue.add(tmp);
                            count[tmp] = count[t] + 1;

                            // 만약 원하던 숫자에 도달하면 끝내
                            if (tmp == ed) {
                                cnt = count[tmp];
                                return;
                            }
                        }
                        // 하나씩만 바뀌어야 하기 때문에, 원복 필수
                        list[i] = ori;
                    }
                }
            }
        }
    }

    // char 배열 숫자로 바꿔주는 메서드
    static int toNum(char[] list) {
        String str = String.valueOf(list);
        return Integer.parseInt(str);
    }

    // 소수 판별 메서드
    static boolean isPrime(int num) {
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
