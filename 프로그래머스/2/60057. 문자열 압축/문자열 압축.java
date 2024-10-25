class Solution {
    public int solution(String s) {
        int answer = s.length();
        
        // 최대 단위는 s/2
        int len = s.length() / 2;
        for (int i = len; i > 0; i--) {
            int st = 0;
            int ed = st + i;
            
            int min = 0; // 문자열 길이
            int rep = 1; // 반복 횟수
            String str = ""; // 문자 단위
            
            while (ed <= s.length()) {
                String t = s.substring(st, ed);
                if (str.equals(t)) {
                    rep++;
                } else {
                    if (rep > 1) {
                        min += String.valueOf(rep).length();
                    }
                    min += t.length();
                    rep = 1;
                    
                }
                str = t;
                st += i;
                ed += i;
            }
            
            // 마지막 처리
            if (rep > 1) {
                min += String.valueOf(rep).length();
            }
            min += s.length() % i;
            
            if (min < answer) answer = min;
            
        }
        
        return answer;
    }
}