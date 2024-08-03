import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] solution(String msg) {
        int[] temp = new int[1000];

        char[] word = msg.toCharArray();
        int len = word.length;

        // 사전 생성 및 초기화
        Map<String, Integer> dict = new HashMap<>();
        for (char i = 'A'; i <= 'Z'; i++) {
            dict.put(String.valueOf(i), (int)i - 64);
        }

        // 가장 긴 문자열 w 찾기
        int num = 0;

        while (true) {
            String w = findW(word, dict);

            // 색인 번호 출력
            temp[num++] = dict.get(w);

            // 다음 글자가 없는 경우 나가
            if (idx == len-1) break;

            // 사전 추가
            dict.put(w + word[++idx], dict.size()+1);
        }

        // 임시 배열에서 정답 배열로 값 복사
        int[] answer = new int[num];
        for (int i = 0; i < num; i++) {
            answer[i] = temp[i];
        }

        return answer;
    }
    static int idx;
    static String findW(char[] word, Map<String, Integer> dict) {
        String w = String.valueOf(word[idx]);

        while (idx+1 != word.length && dict.containsKey(w + word[idx+1])) {
            w += word[++idx];
        }

        return w;
    }
}