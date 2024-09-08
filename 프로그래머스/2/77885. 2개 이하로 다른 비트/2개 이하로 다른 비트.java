class Solution {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];

        for (int k = 0; k < numbers.length; k++) {
            long n = numbers[k];
            String str = "0" + Long.toBinaryString(n);
            char[] bin = str.toCharArray();
            // 가장 작은 자릿수에 있는 0 찾기
            int loc = 0;
            for (int i = bin.length-1; i >= 0; i--) {
                if (bin[i] == '0') {
                    loc = i;
                    break;
                }
            }
            if (loc == 0) {
                // 만약 모든 비트가 1로 구성된 경우 자릿수 +1
                // 두 번째 비트를 0으로 바꿔서 제일 작은 수로 만든다.
                bin[0] = '1';
                bin[1] = '0';
            } else if (loc == bin.length-1){
                bin[loc] = '1';
            } else {
                bin[loc] = '1';
                bin[loc+1] = '0';
            }

            String num = "";
            for (int i = 0; i < bin.length; i++) {
                num += bin[i];
            }

            answer[k] = Long.valueOf(num, 2);

        }
        return answer;
    }
}