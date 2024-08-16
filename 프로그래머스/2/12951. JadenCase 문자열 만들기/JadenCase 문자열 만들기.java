class Solution {
    public String solution(String s) {
        String answer = "";
        
        boolean isFirst = true; // 단어의 첫 문자 여부
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (isFirst && c != ' ') {
                if (c >= 'a' && c <= 'z') {
                    answer += Character.toUpperCase(c);
                } else {
                    answer += c;
                }
                isFirst = false;
                continue;
            }

            if (c == ' ') isFirst = true;
            else {
                if (c >= 'A' && c <= 'Z') {
                    c = Character.toLowerCase(c);
                }
                isFirst = false;
            }

            answer += c;
        }

        return answer;
    }
}