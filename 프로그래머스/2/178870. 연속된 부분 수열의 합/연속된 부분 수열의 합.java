class Solution {
    public int[] solution(int[] sequence, int k) {
        int st = 0;
        int ed = 0;
        int[] answer = new int[]{0, sequence.length-1};

        int sum = sequence[0];
        while (ed < sequence.length) {
            if (sum == k) {
                // 같으면 길이 확인 후 answer 에 저장
                if (answer[1] - answer[0] > ed - st) {
                    answer[0] = st;
                    answer[1] = ed;
                }

                // 초기화
                if (ed - st == 0) {
                    break;
                } else {
                    if (ed + 1 > sequence.length-1) {
                        break;
                    }
                    sum -= sequence[st];
                    sum += sequence[ed+1];
                    
                    int len = ed - st;
                    st++;
                    ed = st + len;
                }

            } else if (sum < k) {
                if (ed+1 < sequence.length) {
                    sum += sequence[++ed];
                } else {
                    ed++;
                }
            } else {
                sum -= sequence[st++];
            }
        }
        
        return answer;
    }
}