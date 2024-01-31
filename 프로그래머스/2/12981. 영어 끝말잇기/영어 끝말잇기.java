import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[] {0, 0};

        Set<String> set = new HashSet<>();
        Character last = null;
        int person = -1;
        boolean flag = false; // 탈락 여부
        int circle = 0; // 몇회차인지

        for (int i = 0; i < words.length; i++) {
            circle = (i / n) + 1;
            // 사람 번호 업데이트
            person = (i % n) + 1;

            // 나온 단어
            String w = words[i];

            // 단어 중복 체크
            if (set.contains(w)) {
                flag = true;
                break;
            }

            char[] word = w.toCharArray();
            // 단어 길이 1이면 탈락
            if (word.length == 1) {
                flag=true;
                break;
            }

            // 단어 첫 글자와 직전 단어의 마지막 글자 비교
            if (last != null && word[0] != last) {
                flag=true;
                break;
            }

            // 나온 단어 리스트에 추가
            // 마지막 글자 last에 담자
            set.add(w);
            last = word[word.length-1];
        }

        if (flag) {
            answer[0] = person;
            answer[1] = circle;
        }
        return answer;
    }
}