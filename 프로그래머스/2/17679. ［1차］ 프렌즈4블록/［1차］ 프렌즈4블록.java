class Solution {
    public int solution(int m, int n, String[] board) {
        int answer = 0;

        char[][] arr = new char[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = board[i].charAt(j);
            }
        }

        int cnt = 1; // 블럭 사라진 횟수
        while (cnt > 0) {
            boolean[][] visited = new boolean[m][n];
            cnt = 0;

            // 2x2 블록 탐색
            for (int i = 0; i < m - 1; i++) {
                for (int j = 0; j < n - 1; j++) {
                    if (arr[i][j] != '.' &&
                            arr[i][j] == arr[i][j + 1] &&
                            arr[i][j] == arr[i + 1][j] &&
                            arr[i][j] == arr[i + 1][j + 1]) {

                        visited[i][j] = true;
                        visited[i][j + 1] = true;
                        visited[i + 1][j] = true;
                        visited[i + 1][j + 1] = true;
                        cnt++;
                    }
                }
            }

            // 제거된 블록 처리
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (visited[i][j]) {
                        arr[i][j] = '.';
                        answer++;
                    }
                }
            }

            // 블록 하강 처리
            // 아래 -> 위로 한줄씩 탐색
            for (int j = 0; j < n; j++) {
                int empty = m - 1;
                for (int i = m - 1; i >= 0; i--) {
                    if (arr[i][j] != '.') {
                        char temp = arr[i][j];
                        arr[i][j] = '.';
                        arr[empty][j] = temp;
                        empty--;
                    }
                }
            }
        }

        return answer;
    }
}