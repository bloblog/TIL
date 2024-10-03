class Solution {
    public int solution(int storey) {
        int answer = 0;

        while (true) {
            // 현재 storey 의 자릿수
            int idx = String.valueOf(storey).length();
//            System.out.println("storey = " + storey + " 자릿수 = " + idx);

            // 한자리 수일 경우
            if (idx == 1) {
                if (storey > 5) answer += 11 - storey;
                else answer += storey;
                break;
            }

            // 현재 숫자
            int t = storey / (int)(Math.pow(10, idx-1));
            
            // 다음 숫자
            int next = (storey - t * (int)(Math.pow(10, idx-1))) / (int)(Math.pow(10, idx-2));

            if (t >= 5) {
                if (t == 5 && next < 5) {
                    answer += t;
                    int floor = t * (int)(Math.pow(10, idx-1));
                    storey -= floor;
                } else {
                    answer += 1;
                    int ceil = (int)(Math.pow(10, idx));
                    storey = ceil - storey;
                }
            } else {
                if (next > 5) {
                    answer += t+1;
                    int ceil = (t+1) * (int)(Math.pow(10, idx-1));
                    storey = ceil - storey;
                } else {
                    answer += t;
                    int floor = t * (int)(Math.pow(10, idx-1));
                    storey -= floor;
                }
            }
        }

        return answer;
    }
}