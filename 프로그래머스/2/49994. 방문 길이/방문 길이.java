class Solution {
    public int solution(String dirs) {
        int answer = 0;

        char[] dirList = dirs.toCharArray();
        int[] dc = new int[] {0, 1, 0, -1}; // 상 우 하 좌
        int[] dr = new int[] {-1, 0, 1, 0};

        boolean[][][] visited = new boolean[11][11][4]; // 들어온 방향까지 고려
        int r = 5; // 초기 위치
        int c = 5;

        for (int i = 0; i < dirList.length; i++) {
            char d = dirList[i];
            int dir = -1; // 알파벳을 델타 인덱스 dir 로 치환
            if (d == 'U') dir = 0;
            if (d == 'R') dir = 1;
            if (d == 'D') dir = 2;
            if (d == 'L') dir = 3;
            
            int nr = r + dr[dir];
            int nc = c + dc[dir];
            int nd = -1;
            
            if (dir == 0) nd = 2;
            if (dir == 1) nd = 3;
            if (dir == 2) nd = 0;
            if (dir == 3) nd = 1;

            if (nr < 0 || nr >= 11 || nc < 0 || nc >= 11) {
                continue;
            }

            if (!visited[nr][nc][nd] && !visited[r][c][dir]) {
                visited[nr][nc][nd] = true;
                answer++;
            }

            r = nr;
            c = nc;
        }

        return answer;
    }
}