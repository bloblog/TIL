class Solution {
    public int solution(int sticker[]) {
        if (sticker.length == 1) {
            return sticker[0];
        }
        // 인덱스 0에서 시작
        // N-2 까지 고려
        int[] dp1 = new int[sticker.length-1];
        for (int i = 0; i < sticker.length-1; i++) {
            dp1[i] = sticker[i];
        }

        for (int i = 1; i < dp1.length; i++) {
            if (i == 1) dp1[i] = Math.max(dp1[0], dp1[i]);
            else {
                dp1[i] = Math.max(dp1[i-1], dp1[i-2] + dp1[i]);
            }
        }


        // 인덱스 1에서 시작
        // N-1 까지 고려
        int[] dp2 = new int[sticker.length-1];
        for (int i = 1; i < sticker.length; i++) {
            dp2[i-1] = sticker[i];
        }

        for (int i = 1; i < dp2.length; i++) {
            if (i == 1) dp2[i] = Math.max(dp2[0], dp2[i]);
            else {
                dp2[i] = Math.max(dp2[i-1], dp2[i-2] + dp2[i]);
            }
        }

        return Math.max(dp1[dp1.length-1], dp2[dp2.length-1]);

    }
    
}