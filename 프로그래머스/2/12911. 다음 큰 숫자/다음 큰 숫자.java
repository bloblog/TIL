class Solution {
    public int solution(int n) {
        int answer = 0;
        String n_bin = Integer.toBinaryString(n);

        // 1 개수 카운트
        int cnt = countOne(n_bin);


        while (true) {
            n++;
            if (countOne(Integer.toBinaryString(n)) == cnt) {
                return n;
            }
            if (n > 1000064) break;
        }

        return -1;
    }
    
    static int countOne(String s) {
        int cnt = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') cnt++;
        }
        return cnt;
    }
}