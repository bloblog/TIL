import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n1 = sc.nextInt();
        int n2 = sc.nextInt();

        if (n1 == n2) System.out.println(n1 + " " + n2);
        else {

        }// n2 의 약수들 중
        List<Integer> num1 = div(n2);

        // n1 의 배수들을 구해서
        List<Integer> num2 = new ArrayList<>();
        for (int i = 0; i < num1.size(); i++) {
            if (num1.get(i) % n1 == 0) {
                num2.add(num1.get(i));
            }
        }

        // 리스트 -> 배열
        int[] res = new int[num2.size()];
        for (int i = 0; i < num2.size(); i++) {
            res[i] = num2.get(i);
        }
        Arrays.sort(res);

        // 양끝부터 짝궁 찾아서 합이 최소인 두 수 출력
        int min = Integer.MAX_VALUE;
        String answer = "";
        for (int i = 0; i < num2.size()/2; i++) {
            int val1 = res[i];
            int val2 = res[num2.size()-1-i];

            // 검증 및 최소인지 판별
            if (val1+val2 < min && check(val1, val2, n1, n2)) {
                min = val1 + val2;
                answer = val1 + " " + val2;
            }
        }

        System.out.println(answer);


    }

    static boolean check(int n1, int n2, int t1, int t2) {
        // n1, n2는 검증할 숫자
        // t1, t2 는 최대공약수, 최소공배수

        // 최대공약수 검증
        List<Integer> num1 = div(n1);
        List<Integer> num2 = div(n2);
        int max = 0;
        for (int i : num1) {
            for (int j : num2) {
                if (i == j && i > max) {
                    max = i;
                }
            }
        }
        if (max != t1) return false;

        // 최소공배수 검증
        int min = 0;
        while (true) {
            min += n2;
            List<Integer> nums = div(min);
            for (int i : nums) {
                if (i % n1 == 0) {
                    if (i != t2) return false;
                    else return true;
                }
            }
        }
    }

    static List div(int n) {
        // 약수 구하는 메서드
        List<Integer> nums = new ArrayList<>();
        for (int i = 1; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                nums.add(i);
                nums.add(n / i);
            }
        }
        return nums;
    }
}
