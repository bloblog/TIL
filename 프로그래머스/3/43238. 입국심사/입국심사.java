import java.util.Arrays;

class Solution {
    public long solution(int n, int[] times) {
        int len = times.length;
        Arrays.sort(times);

        long left = 1;
        long right = n * (long)times[len-1];
        long min = Long.MAX_VALUE;

        while (left < right) {
            long mid = (left + right) / 2; // 심사 시간

            // 심사 가능한 인원
            long sum = 0;
            for (int t : times) {
                sum += mid / t;
            }

            if (sum >= n) {
                // 심사 가능한 인원이 n보다 크면 심사 시간을 줄인다
                right = mid;
                if (mid < min) min = mid;
            } else {
                left = mid + 1;
            }
        }

        return min;
    }
}