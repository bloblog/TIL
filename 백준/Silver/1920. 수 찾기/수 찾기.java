import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        long[] arr = new long[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        long[] target = new long[M];
        for (int i = 0; i < M; i++) {
            target[i] = Long.parseLong(st.nextToken());
        } // 인풋 받기 끝

        // arr 정렬 후 이분탐색 수행
        Arrays.sort(arr);

        for (int i = 0; i < M; i++) {
            if (Arrays.binarySearch(arr, target[i]) >= 0) {
                // 있으면 인덱스 반환 = 인덱스는 무조건 양수
                bw.write("1\n");
            } else {
                bw.write("0\n");
            }
        }

        bw.flush();

    }
}
