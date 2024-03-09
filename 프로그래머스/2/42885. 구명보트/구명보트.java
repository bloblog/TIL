import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;

        Arrays.sort(people);

        int l_idx = 0;
        int r_idx = people.length-1;

        while (l_idx <= r_idx) {
            int min = people[l_idx];
            int max = people[r_idx];

            if (min + max > limit) {
                answer++;
                r_idx--;
            } else {
                answer++;
                l_idx++;
                r_idx--;
            }
        }
        return answer;
    }
}