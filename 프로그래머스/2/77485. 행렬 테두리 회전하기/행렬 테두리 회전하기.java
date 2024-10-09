import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];

        int[][] board = new int[rows][columns];
        int num = 1;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                board[r][c] = num++;
            }
        }

        for (int i = 0; i < queries.length; i++) {
            answer[i] = rotate(queries[i], board).poll();
        }

        return answer;
    }
    
    static PriorityQueue<Integer> rotate(int[] q, int[][] board) {
        // board 회전 및 바뀐 숫자들 반환
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(board[q[0]-1][q[1]-1]);

        int r = q[0]-1;
        int c = q[1];
        while (true) {
            if (r == q[0]-1 && c == q[1]-1) break;

            // board[q[0]-1][q[1]-1] 와 현재 숫자를 계속 바꿔
            int temp = board[q[0]-1][q[1]-1];
            board[q[0]-1][q[1]-1] = board[r][c];
            board[r][c] = temp;

            pq.add(board[q[0]-1][q[1]-1]);

            // r, c 갱신
            if (r == q[0]-1 && c < q[3]-1) {
                c++;
            } else if (r < q[2]-1 && c == q[3]-1) {
                r++;
            } else if (r == q[2]-1 && c > q[1]-1) {
                c--;
            } else {
                r--;
            }
        }
        return pq;
    }
}