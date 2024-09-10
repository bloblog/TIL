class Solution {
    public int[] solution(int n) {
        // 총 칸 수 계산
        int len = n * (n+1) / 2;
        int[] answer = new int[len];

        int num = 1; // 배열에 들어갈 숫자
        int idx = 0; // 배열 인덱스
        int amount = 0; // 증감
        int state = n; // 증가 감소 1씩증가 구분

        while (num <= len) {
            for (int i = 0; i < state; i++) {
                if ((n-state) % 3 == 0) {
                    // 증가 상태일 경우
                    idx += amount++;
                    answer[idx] = num++;
                } else if ((n-state) % 3 == 1) {
                    // 일정증가 상태인 경우
                    answer[++idx] = num++;
                } else {
                    // 감소 상태인 경우
                    idx -= amount--;
                    answer[idx] = num++;
                }
            }
            state--;
        }

        return answer;
    }
}