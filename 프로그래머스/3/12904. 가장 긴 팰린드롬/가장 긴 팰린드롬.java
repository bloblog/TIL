class Solution {
    public int solution(String s) {
        int answer = 0;

        for (int st = 0; st < s.length(); st++) {
            // 현재 최장길이보다 긴 경우만 본다
            for (int ed = st + answer; ed < s.length(); ed++) {
                if (check(st, ed, s) && answer < ed - st + 1) {
//                    System.out.println("answer 갱신 st = " + st + " ed = " + ed);
                    answer = ed - st + 1;
                }
            }
        }

        return answer;
    }
    
    static boolean check(int st, int ed, String s) {
        // st ~ ed 까지의 부분문자열이 팰린드롬인지 체크
        int cnt = (ed - st) / 2 + 1;
        for (int i = 0; i < cnt; i++) {
            if (s.charAt(st+i) != s.charAt(ed-i)) {
                return false;
            }
        }
        return true;
    }
}