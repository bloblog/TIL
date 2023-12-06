class Solution {
    
    static int num;
    static String[] str;
    static char[] member = new char[] {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    
    public int solution(int n, String[] data) {
        num = n;
        str = data;
        cnt = 0;
        
        // 일단 냅다 순열 만들고 그 안에서 처리
        perm(0);
        return cnt;
    }
    
    static boolean[] visited = new boolean[8];
    static char[] res = new char[8];
    static int cnt; // 경우의 수 카운트
    
    static void perm(int idx) {
        if (idx == 8) {
            // 조건 체크 후 맞으면 +1
            if (check(res)) {
                cnt++;
            }
            return;
        }

        for (int i = 0; i < 8; i++) {
            if (!visited[i]) {
                visited[i] = true;
                res[idx] = member[i];
                perm(idx+1);
                visited[i] = false;
            }


        }
    }
    
    static boolean check(char[] arr) {
        for (int i = 0; i < num; i++) {
            char[] con = str[i].toCharArray();
            char t1 = con[0];
            char t2 = con[2];
            char opt = con[3];
            int interval = Integer.parseInt(String.valueOf(con[4]));

            // t1 과 t2의 실제 간격(itv) 파악
            int itv = 0;
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] == t1) {
                    itv += j;
                } else if (arr[j] == t2) {
                    itv -= j;
                }
            }
            // 사이에 있는 친구들 수니까
            // 만약 3, 4 서있으면 0 나와야 함
            itv = Math.abs(itv)-1;

            if (opt == '=') {
                if (itv != interval) return false;
            } else if (opt == '<') {
                if (itv >= interval) return false;
            } else {
                if (itv <= interval) return false;
            }
        }
        // 다 돌았는데 조건 다 만족한 경우 true 반환
        return true;
    }
}