import java.util.Arrays;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int N = commands.length;
        
        int[] answer = new int[N];
        int idx = 0;

        for (int i = 0; i < N; i++) {
            int lidx = 0;
            int[] list = new int[commands[i][1] - commands[i][0] + 1];
            for (int j = commands[i][0]; j <= commands[i][1]; j++) {
                list[lidx++] = array[j - 1];
            }
            Arrays.sort(list);
            answer[idx++] = list[commands[i][2] - 1];
        }
        
        return answer;
    }
}