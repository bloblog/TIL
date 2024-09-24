import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class Solution {
    public int solution(String[][] book_time) {
        int n = book_time.length;

        int[][] numArr = new int[n][2];

        for (int i = 0; i < n; i++) {
            numArr[i] = new int[] {toNum(book_time[i][0]), toNum(book_time[i][1])+10};
        }

        Arrays.sort(numArr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        // 방별 종료시간 담는 리스트
        List<Integer> end = new ArrayList<>();
        for (int[] a : numArr) {
            if (end.size() == 0) {
                end.add(a[1]);
            } else {
                boolean flag = false;
                for (Integer e : end) {
                    if (e <= a[0]) {
                        end.remove(e);
                        end.add(a[1]);
                        flag = true;
                        break;
                    }
                }
                if (!flag) end.add(a[1]);
            }
        }

        return end.size();
    }
    
    static int toNum(String str) {
        String[] s = str.split(":");
        int h = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]);
        return h*60 + m;
    }
}