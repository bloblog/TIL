class Solution {
    public int[] solution(int[][] arr) {
        int[] answer = new int[2];

        int n = arr.length;
        int s = arr.length; // 영역 시작하는 행 및 열 위치
        boolean[][] visited = new boolean[n][n]; // 압축 여부

        // 0, 1의 개수 초기 세팅
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] == 0) answer[0]++;
                else answer[1]++;
            }
        }

        while (s > 1) {
            for (int i = 0; i < n; i += s) {
                for (int j = 0; j < n; j += s) {
                    if (!visited[i][j] && check(i, j, s, arr)) {
                        // 모두 같다면 해당 숫자(t)로 압축
                        int t = arr[i][j];
                        zip(i, j, t, s, answer, visited);
                    };
                }
            }
            s /= 2;
        }


        return answer;
    }
    
    static void zip(int i, int j, int t, int s, int[] answer, boolean[][] visited) {
        // 압축 여부 처리
        for (int r = i; r < i + s; r++) {
            for (int c = j; c < j + s; c++) {
                visited[r][c] = true;
            }
        }

        // 압축시 s*s개의 t -> 1개의 t
        if (t == 0) {
            answer[0] -= s*s-1;
        } else {
            answer[1] -= s*s-1;
        }
    }

    static boolean check(int i, int j, int s, int[][] arr) {
        // arr의 i행 j열부터 위아래로 s만큼 확인했을 때
        // 숫자가 다 같은지 체크
        int t = arr[i][j];
        for (int r = i; r < i + s; r++) {
            for (int c = j; c < j + s; c++) {
                if (arr[r][c] != t) return false;
            }
        }
        return true;
    }
}