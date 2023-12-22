import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            int answer = 0;
            char[] target = sc.next().toCharArray();

            int p1 = 0; // 포인터 1
            int p2 = target.length-1; // 포인터 2

            while (p1 < p2) {
                if (target[p1] == target[p2]) {
                    p1++;
                    p2--;
                } else {
                    if (isPal(target, p1)) {
                        // p1 제거했을 때 회문이면
                        answer = 1;
                        break;
                    }
                    if (isPal(target, p2)) {
                        // p2 제거했을 때 회문이면
                        answer = 1;
                        break;
                    }
                    // 둘 다 아니면 일반 문자열
                    answer = 2;
                    break;
                }
            }

            System.out.println(answer);
        }
    }

    // 특정 인덱스 제거했을 때 회문인가?
    static boolean isPal(char[] t, int idx) {
        // 앞에서 시작, 뒤에서 시작하는 투 포인터로 회문 체크
        int p1 = 0; // 포인터 1
        int p2 = t.length-1; // 포인터 2

        while (p1 < p2) {
            // 특정 인덱스 삭제 처리
            if (p1 == idx) p1++;
            else if (p2 == idx) p2--;

            if (t[p1] == t[p2]) {
                p1++;
                p2--;
            } else {
                return false;
            }
        }
        return true;
    }
}
