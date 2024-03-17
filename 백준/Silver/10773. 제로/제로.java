import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int K = sc.nextInt();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < K; i++) {
            int val = sc.nextInt();
            if (val == 0) {
                stack.pop();
            } else {
                stack.push(val);
            }
        }
        int sum = 0;
        while (!stack.isEmpty()) {
            sum += stack.pop();
        }

        System.out.println(sum);
    }
}
