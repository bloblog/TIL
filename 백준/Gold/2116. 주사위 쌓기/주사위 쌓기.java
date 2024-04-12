import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[][] arr = new int[n][6];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 6; j++) {
                arr[i][j] = sc.nextInt();
            }
        } // 인풋 받기 끝

        // 첫번째 주사위만 결정되면 다른 주사위들도 결정된다
        int max = 0;
        for (int i = 0; i < 6; i++) {
            int sum = 0;
            // 주사위의 윗면 up
            int up = arr[0][i];

            sum += getMax(up, arr[0]);

            for (int j = 1; j < n; j++) {
                // 나머지 주사위 최대값 가져오기
                sum += getMax(bottom, arr[j]);
                
            }

            if (sum > max) max = sum;
            
        }

        System.out.println(max);
    }

    static int bottom; // 주사위 밑면
    static int getMax(int up, int[] arr) {
        int[] copy = Arrays.copyOf(arr, 6);
        int maxVal = 0;
        int idx = -1;

        for (int i = 0; i < 6; i++) {
            if (arr[i] == up) {
                idx = i;
                copy[i] = 0;
                break;
            }
        }

        if (idx == 0) {
            copy[5] = 0;
            bottom = arr[5];
        }
        if (idx == 1) {
            copy[3] = 0;
            bottom = arr[3];
        }
        if (idx == 2) {
            copy[4] = 0;
            bottom = arr[4];
        }
        if (idx == 3) {
            copy[1] = 0;
            bottom = arr[1];
        }
        if (idx == 4) {
            copy[2] = 0;
            bottom = arr[2];
        }
        if (idx == 5) {
            copy[0] = 0;
            bottom = arr[0];
        }

        for (int i = 0; i < 6; i++) {
            if (copy[i] > maxVal) maxVal = copy[i];
        }
        return maxVal;
    }
}

