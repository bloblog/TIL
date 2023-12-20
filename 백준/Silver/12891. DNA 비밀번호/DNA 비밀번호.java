import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int s = sc.nextInt();
        int p = sc.nextInt();

        char[] arr = sc.next().toCharArray();

        // 각 알파벳의 최소 개수
        int[] condition = new int[] {sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt()};

        // 비밀번호 종류의 수
        int cnt = 0;

        // arr 배열에서 p개 떼서 알파벳 개수 카운트
        int[] count = new int[4];

        for (int i = 0; i < p; i++) {
            if (arr[i] == 'A') count[0]++;
            else if (arr[i] == 'C') count[1]++;
            else if (arr[i] == 'G') count[2]++;
            else if (arr[i] == 'T') count[3]++;
        }

        // 조건 만족시 cnt+1
        if (check(count, condition)) {
            cnt++;
        }

        // 다음 비밀번호부터는 맨 앞 번호 제외, 다음 번호 추가만 하면 된다
        for (int i = 0; i < s-p; i++) {
            if (arr[i] == 'A') count[0]--;
            else if (arr[i] == 'C') count[1]--;
            else if (arr[i] == 'G') count[2]--;
            else if (arr[i] == 'T') count[3]--;

            if (arr[p+i] == 'A') count[0]++;
            else if (arr[p+i] == 'C') count[1]++;
            else if (arr[p+i] == 'G') count[2]++;
            else if (arr[p+i] == 'T') count[3]++;

            if (check(count, condition)) {
                cnt++;
            }
        }
        System.out.println(cnt);
    }

    static boolean check(int[] count, int[] con) {
        for (int i = 0; i < 4; i++) {
            if (count[i] < con[i]) return false;
        }
        return true;
    }
}
