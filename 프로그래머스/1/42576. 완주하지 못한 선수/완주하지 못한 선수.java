import java.util.Arrays;


class Solution {
    public String solution(String[] participant, String[] completion) {
        // 배열 정렬
        Arrays.sort(participant);
        Arrays.sort(completion);

        // 배열의 요소가 다를 때까지 탐색
        int idx = 0;
        for(int i = 0; i < completion.length; i++) {
            if (!participant[i].equals(completion[i])) {
                idx = i;
                break;
            }
            idx++;
        }

        return participant[idx];
    }
}