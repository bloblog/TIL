import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Integer[] arr = new Integer[N];
        int neg = 0;
        int zero = 0;
        for (int i = 0; i< N; i++) {
            int val = Integer.parseInt(br.readLine());
            arr[i] = val;
            if (val < 0) neg++;
            if (val == 0) zero++;
        } // 인풋 받기 끝

        Arrays.sort(arr, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });


        int answer = 0;
        for (int i = 0; i < N-neg-zero; i++) {
            // 양수 먼저 돌아
            // 1이면 그냥 더해
            if (arr[i] == 1) {
                answer += arr[i];
                continue;
            }

            if (i+1 < N-neg-zero && arr[i+1] != 1) {
                int n1 = arr[i];
                int n2 = arr[i+1];
                answer += n1*n2;
                i++;

            } else {
                // 남은 수가 홀수인 경우
                answer += arr[i];
            }
        }

        // 나머지 거꾸로 돌아
        Arrays.sort(arr, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        for (int i = 0; i < neg; i++) {
            if (i+1 < neg) {
                int n1 = arr[i];
                int n2 = arr[i+1];
                answer += n1*n2;
                i++;

            } else {
                // 남은 수가 홀수인 경우
                // 0 있으면 곱해서 없애고, 아니면 그냥 더해
                if (zero == 0) {
                    answer += arr[i];
                }
            }
        }

        System.out.println(answer);

    }
}
