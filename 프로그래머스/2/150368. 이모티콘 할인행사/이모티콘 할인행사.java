class Solution {
    public int[] solution(int[][] users, int[] emoticons) {
        answer = new int[2];

        uList = users;
        emoList = emoticons;

        n = users.length;
        m = emoticons.length;

        sel = new int[m];
        nums = new int[] {10, 20, 30, 40};
        comb(0);

        return answer;
    }
    static int n, m;
    static int[] sel, nums, emoList, answer;
    static int[][] uList;
    
    static void comb(int sIdx) {
        if (sIdx == m) {
            // 할인율에 맞게 계산
            calc();
            return;
        }

        for (int i = 0; i < 4; i++) {
            sel[sIdx] = nums[i];
            comb(sIdx+1);
        }

    }

    static void calc() {
        int[] result = new int[2];
        int cnt = 0; // 플러스 가입자 수
        int sum = 0; // 이모티콘 매출액

        for (int i = 0; i < n; i++) {
            int uSum = 0; // 해당 유저의 예상 구매 비용
//            System.out.println("유저 : " + Arrays.toString(uList[i]));
            for (int j = 0; j < m; j++) {
                if (uList[i][0] <= sel[j]) {
                    // 구매
                    uSum += (int)(emoList[j] * (100-sel[j]) * 0.01);
//                    System.out.println("원가 : " + emoList[j] + " 할인가 : " + (int)(emoList[j] * (100-sel[j]) * 0.01));
                }
            }
            if (uSum >= uList[i][1]) {
                cnt++; // 플러스 가입
            } else {
                sum += uSum; // 가입x 매출만
            }
        }

        if (answer[0] < cnt) {
            answer[0] = cnt;
            answer[1] = sum;
        } else if (answer[0] == cnt && answer[1] < sum) {
            answer[1] = sum;
        }

    }
}