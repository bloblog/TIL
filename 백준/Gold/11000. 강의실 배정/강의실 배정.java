import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<int[]> pq = new PriorityQueue(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                int[] arr1 = (int[]) o1;
                int[] arr2 = (int[]) o2;
                if (arr1[0] == arr2[0]) {
                    return arr1[1] - arr2[1];
                }
                return arr1[0] - arr2[0];
            }
        });

        for (int i= 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            pq.add(new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        } // 인풋 받기 끝

        PriorityQueue<Integer> classroom = new PriorityQueue(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return (int) o1 - (int) o2;
            }
        });
        while (!pq.isEmpty()) {
            int[] now = pq.poll();
            boolean flag = false; // 현존하는 강의실에 넣을 수 있는지?

            if (classroom.isEmpty() || classroom.peek() > now[0]) {
                classroom.add(now[1]);
            } else {
                classroom.poll();
                classroom.add(now[1]);
            }
        }

        System.out.println(classroom.size());

    }
}